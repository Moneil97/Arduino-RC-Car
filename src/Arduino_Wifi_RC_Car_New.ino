
int rearEnablePin = 3; //Controls Speed
int rearLogic1Pin = 5; 
int rearLogic2Pin = 2; //If same will turn motor off, if differnt will change polarity;

int frontLogic1Pin = 7;
int frontLogic2Pin = 8;
int vel = 200;

//------------------------------------  Debugging -----------------------------------------
#include <SPI.h>         // needed for Arduino versions later than 0018
#include <Ethernet.h>
#include <EthernetUdp.h>         // UDP library from: bjoern@cs.stanford.edu 12/30/2008


// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192, 168, 1, 202);

unsigned int localPort = 8888;      // local port to listen on

// buffers for receiving and sending data
//char packetBuffer[UDP_TX_PACKET_MAX_SIZE]; //buffer to hold incoming packet,
char  ReplyBuffer[] = "acknowledged";       // a string to send back

// An EthernetUDP instance to let us send and receive packets over UDP
EthernetUDP udp;

boolean debug = true;

//------------------------------------  /Debugging -----------------------------------------

void setup() {
  
  pinMode(3, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  
  
  pinMode(8, OUTPUT);
  if (debug){
    // start the Ethernet and UDP:
    Ethernet.begin(mac,ip);
    udp.begin(localPort);
  }

  Serial.begin(9600);
  Serial.println("AT+CIPMUX=1");
  delay(1000);
  Serial.println("AT+CIPSERVER=1,1336");
}

void loop() {
  
  if (debug){
      // if there's data available, read a packet
    int packetSize = udp.parsePacket();
    if(packetSize)
    {
        IPAddress remote = udp.remoteIP();
        // read the packet into packetBufffer
        char packetBuffer [packetSize];
        udp.read(packetBuffer, packetSize);
   
        String out = "";
        for (int i = 0; i < sizeof(packetBuffer) / sizeof(char); i++){
          out += (char) packetBuffer[i];
        }
      
        Serial.println(out);
        out = "Arduino received and sent to WIFI: " + out;
        sendToDebugger(out);
      }
    }
  
  
  while (Serial.available() > 0){
    String output = "";
    while (Serial.available() > 0){
        output += (char) Serial.read();
        if (output.endsWith("\\"))
          break;
        delay(10);
    }
    
    sendToUDP(output);
    
    if (output.indexOf("/") > -1){
    String command = output.substring(output.indexOf("/")+1, output.lastIndexOf("\\"));
    sendToDebugger("Command: " + command);
    
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

void sendToDebugger(String s){
  
  if (debug){
  
      udp.beginPacket(udp.remoteIP(), udp.remotePort());
    
      for (int i = 0; i < s.length(); i++){
        udp.write(s.charAt(i));
      }
    
      udp.endPacket();
  }
}

void straight(){
  
}

void left(){
  
}

void right(){
  
}

void still(){
  
}

void forward(){
  
}

void reverse(){
  
}


