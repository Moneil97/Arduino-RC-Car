
int rearEnablePin = 3; //Controls Speed
int rearLogic1Pin = 5; 
int rearLogic2Pin = 2; //If same will turn motor off, if differnt will change polarity;

int frontLogic1Pin = 7;
int frontLogic2Pin = 8;
int vel = 200;

void setup() {
  
  pinMode(3, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);

  Serial.begin(9600);
  Serial.println("AT+CIPMUX=1");
  delay(1000);
  Serial.println("AT+CIPSERVER=1,1336");
}

void loop() {
  
  while (Serial.available() > 0){
    String output = "";
    while (Serial.available() > 0){
        output += (char) Serial.read();
        if (output.endsWith("\\"))
          break;
        delay(10);
    }
    
    //sendToDebugger(output);
    
    if (output.indexOf(":") > -1){
    String command = output.substring(output.indexOf(":")+1, output.indexOf(":")+3);
    //sendToDebugger("Command: " + command);
    
      if (command.charAt(0) == '0'){
        straight();
      }
      else  if (command.charAt(0) == '1'){
        left();
      }
      else  if (command.charAt(0) == '2'){
        right();
      }
      
      if (command.charAt(1) == '0'){
        still();
      }
      else if (command.charAt(1) == '1'){
        forward();
      }
      else if (command.charAt(1) == '2'){
        reverse();
      }
  }
 }
  
  delay(10);
}

void still(){
  analogWrite(rearEnablePin, 0);
  digitalWrite(rearLogic1Pin, LOW);
  digitalWrite(rearLogic2Pin, LOW);
}

void reverse(){
  analogWrite(rearEnablePin, vel);
  digitalWrite(rearLogic1Pin, HIGH);
  digitalWrite(rearLogic2Pin, LOW);
}

void forward(){
  analogWrite(rearEnablePin, vel);
  digitalWrite(rearLogic1Pin, LOW);
  digitalWrite(rearLogic2Pin, HIGH);
}

void left(){
  digitalWrite(frontLogic1Pin, LOW);
  digitalWrite(frontLogic2Pin, HIGH);
}

void right(){
  digitalWrite(frontLogic1Pin, HIGH);
  digitalWrite(frontLogic2Pin, LOW);
}

void straight(){
  digitalWrite(frontLogic1Pin, LOW);
  digitalWrite(frontLogic2Pin, LOW);
}


