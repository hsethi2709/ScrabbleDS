# README document for Communication Protocols and Server

## 0. Introduction

Hello, guys. 

Sorry for a little bit late but I have spent around two days in implementing and modifying implementation details about Protocols and Server in order to make them working and covering all potential scenarios as well as keeping concise and convenient to use. Unfortunately, they still seem not pretty perfect but I release them so that we can keep on implementing other components of our game system.

I mainly implemented two components: Communication Protocols and working(but still dummy) server. 
The protocol design is based on Gson library(Doc: https://github.com/google/gson/blob/master/UserGuide.md) by which we can convert Java Objects into JSON and back.
The server is designed to reply clients' requests and broadcast information to players. 

I'll explain implementation details in following sections.

## 1. Communication Protocols

I used Json files as medium to implement communication between client and server. 
Essentially, Json files are just structured text files(Key-Value pairs). Compared to XML files, they are more suitable for Java programs. 
On top of that, they are more readable and extendable in case we need to modify communication protocols.

There are two procedures when using Json for communication: 
Convert message to Json files(Serialization) on sender side and Transform back on receiver side(Deserialization).
Following Internet Protocols, I design a protocol pattern like this: 
```
Header(String indicating the type of this message) and Content(The specific values contained in this message).
```
Briefly introduce how to implement customized protocols by Gson library. Take Login communication as example:

a) For Login communication, client should send user's input username to server and get response from server. Therefore, we make a Login Class containing one instance variable named username and then instance it: 
```Java
Login login = new Login("Harshit");
```
b) Then client use Packet Class to encapsulate the message with a Header: 
```Java
Packet<Login> packet = new Packet<Login>("Login", login); 
```
and convert it to Json files: 
```Java
String json = gson.toJson(packet);  
```
Then sent it to Server: 
```Java
out.write(json + "\n");
out.flush;
```
c) Server receive it and try read its Header: 
```Java
String receiveJson = in.readLine();
String header = gson.fromJson(receiveJson, Packet.Class);
```
Then server will judge its type, if it's a Login Packet, convert it back to Login Class and read its content:
```Java
if(header.equals("Login"))
{
    Type type = new TypeToken<Packet<Login>>() {}.getType()
    Login receiveContent = gson.fromJson(receiveJson, type);
    String receiveUsername = receiveContent.getUsername();
}
```
d) Similarly, server can implement a Reply Class to response client's request.

However, although this pattern is very well-structured and easy to extend and modify according to specific requirements, I think it's a little bit too verbose. A more simple way is to combine all types of protocols into single one and implement corresponding different constructors. I cannot decide which one is better and want to listen to your opinions.

## 2. Server

I just implemented a pretty dummy server. It keeps listening to connection and use ClientServeThread Class to implement multi-threading. I didn't write any judgment statements. The server will just simply response that client's request is successful and broadcast it to every player.

It uses Java's ThreadPool to monitor all threads. Use ConcurrentHashMap<String, ServeClientThread> to collect all client threads with username as Key. I take a ArrayList<String> to store all waiting clients' username. It's not necessary -- We can access HashMap to get all keys as waitingList but I prefer to take an individual one to improve performance.

The proper and safe exit strategy for server has not implemented yet, I will finish it later. It's not the first stage requirement after all.

## 3. Client

I also implemented a simple client for testing and demonstration. It has two thread: Main thread for output and ListeningThread for keeping listening to input from server. The client send a Login Packet to Server and receive response from server.

## 4. Next-stage Plan

Tomorrow I will supplement necessary comments to my codes and update some diagrams to clearly explain implementation details.

The server is not satisfactory at all. A lot of modifications need to be done. If you have any questions and suggestions, don't hesitate to discuss with me. Hope to see u guys next time.


