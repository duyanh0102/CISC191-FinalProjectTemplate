package edu.sdccd.cisc191.template;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a ServerSocket that listens on port 4444
            ServerSocket serverSocket = new ServerSocket(4444);

            // Wait for a client to connect
            System.out.println("Waiting for client to connect...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Create input and output streams for the socket
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Read messages from the client and respond accordingly
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received message from client: " + message);
                if (message.equals("get students")) {
                    // Respond with a list of students
                    writer.println("John, Mary, Tom");
                } else if (message.equals("add student")) {
                    // Add a student to the list
                    // Code for adding a student goes here
                    writer.println("Student added.");
                } else {
                    // Invalid command
                    writer.println("Invalid command.");
                }
            }

            // Close the socket
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
