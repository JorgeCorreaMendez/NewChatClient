package com.yogusoft;

import com.yogusoft.chatService.Message;
import com.yogusoft.consoleUtils.ConsoleInput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8082);
             ObjectOutputStream objOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objInputStream = new ObjectInputStream(socket.getInputStream())) {

            List<Message> messageList = (List<Message>) objInputStream.readObject();

            if(!messageList.isEmpty()){
                System.out.println("------------------------------------------");
                System.out.println("Previous messages:");

                messageList.forEach(System.out::println);
            }

            System.out.print("Enter the username: ");
            String username = ConsoleInput.getString();
            objOutputStream.writeObject(username);

            System.out.println("------------------------------------------");

            String newMessage = "";
            while(!newMessage.equals("bye")) {
                List<Message> recentMessages = (List<Message>) objInputStream.readObject();

                if (!recentMessages.isEmpty()){
                    System.out.println("The following messages have arrived: ");
                    messageList.addAll(recentMessages);

                    recentMessages.forEach(System.out::println);
                    System.out.println("------------------------------------------");
                }

                System.out.println("Enter a new message: ");
                newMessage = ConsoleInput.getString();
                objOutputStream.writeObject(newMessage);

                System.out.println("------------------------------------------");

                Object returnMessage = objInputStream.readObject();

                if(returnMessage instanceof Message){
                    System.out.println(returnMessage);
                    messageList.add((Message) returnMessage);

                    int lastMessageIndex = messageList.indexOf(returnMessage);
                    objOutputStream.writeObject(lastMessageIndex);
                } else {
                    System.out.println(returnMessage);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            String messageErr = e.getMessage();

            if (messageErr == null) {
                e.printStackTrace();
            } else {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
