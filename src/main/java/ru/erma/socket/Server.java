// Server.java
package ru.erma.socket;

import ru.erma.socket.handler.CommandParser;
import ru.erma.socket.handler.ServerMessageHandler;
import ru.erma.util.Application;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    public static void main(String[] args) {
        try( ServerSocket server = new ServerSocket(4004) ) {
            Application app = Application.runApp();
            CommandParser commandParser = new CommandParser();
            ServerMessageHandler messageHandler = new ServerMessageHandler(app, commandParser);

            System.out.println("Сервер запущен!");

            while (true) {
                Socket clientSocket = server.accept();
                messageHandler.handleClient(clientSocket);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}