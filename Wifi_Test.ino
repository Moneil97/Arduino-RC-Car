/*
  UDPSendReceive.pde:
 This sketch receives UDP message strings, prints them to the serial port
 and sends an "acknowledge" string back to the sender
 
 A Processing sketch is included at the end of file that can be used to send 
 and received messages for testing with a computer.
 
 created 21 Aug 2010
 by Michael Margolis
 
 This code is in the public domain.
 */
 
int rearEnablePin = 3; //Controls Speed
int rearLogic1Pin = 5; 
int rearLogic2Pin = 2; //If same will turn motor off, if differnt will change polarity;

int frontLogic1Pin = 7;
int frontLogic2Pin = 8;
int vel = 200;


#include <SPI.h>         // needed for Arduino versions later than 0018
#include <Ethernet.h>
#include <EthernetUdp.h>         // UDP library from: bjoern@cs.stanford.edu 12/30/2008


// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = {  
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192, 168, 1, 202);

unsigned int localPort = 8888;      // local port to listen on

// buffers for receiving and sending data
//char packetBuffer[UDP_TX_PACKET_MAX_SIZE]; //buffer to hold incoming packet,
char  ReplyBuffer[] = "acknowledged";       // a string to send back

// An EthernetUDP instance to let us send and receive packets over UDP
EthernetUDP udp;

void setup() {
  
  pinMode(8, OUTPUT);
  
  // start the Ethernet and UDP:
  Ethernet.begin(mac,ip);
  udp.begin(localPort);

  Serial.begin(9600);
}

void loop() {
  // if there's data available, read a packet
  int packetSize = udp.parsePacket();
  if(packetSize)
  {
    IPAddress remote = udp.remoteIP();

    // read the packet into packetBufffer
    char packetBuffer [packetSize];
    udp.read(packetBuffer, packetSize/*UDP_TX_PACKET_MAX_SIZE*/);
    //String message(packetBuffer);
//    Serial.println("Contents:");
//    Serial.println(packetBuffer);
    
    if (packetBuffer[0] == '3'){
      if (packetBuffer[1] == '7'){
        on();
        Serial.println("AT");
      }
//      else if (packetBuffer[1] == '6')
//        straight();
      else if (packetBuffer[1] == '9'){
        off();
//        udp.println("OFF");
//        udp.send("OFF");
      }
    }

  
//    // send a reply, to the IP address and port that sent us the packet we received
//    udp.beginPacket(udp.remoteIP(), udp.remotePort());
////    Udp.write(ReplyBuffer);
//     udp.write(packetBuffer);
//    udp.endPacket();
  }
  
  delay(10);
  
  if (Serial.available() > 0){
    String output = "";
    while (Serial.available() > 0){
//      char buffer = Serial.read();
        output += (char) Serial.read();
    }
    
    udp.beginPacket(udp.remoteIP(), udp.remotePort());
    
    
    for (int i = 0; i < output.length(); i++){
      udp.write(output.charAt(i));
    }
    
//    udp.write(output);
    udp.endPacket();
    
  }
  
  
}

void on(){
//  digitalWrite(frontLogic1Pin, LOW);
//  digitalWrite(frontLogic2Pin, HIGH);
    digitalWrite(8, HIGH);
    udp.beginPacket(udp.remoteIP(), udp.remotePort());
    udp.write("ON");
    udp.endPacket();
}

void off(){
//  digitalWrite(frontLogic1Pin, HIGH);
//  digitalWrite(frontLogic2Pin, LOW);
    digitalWrite(8, LOW);
     udp.beginPacket(udp.remoteIP(), udp.remotePort());
    udp.write("OFF");
    udp.endPacket();
}


