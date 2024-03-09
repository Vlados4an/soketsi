package ru.erma.socket;

import ru.erma.socket.handler.CommandParser;
import ru.erma.socket.handler.ServerMessageHandler;
import ru.erma.util.Application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try( ServerSocket server = new ServerSocket(4004) ) {
            CommandParser commandParser = new CommandParser();
            ServerMessageHandler messageHandler = new ServerMessageHandler(Application.runApp(), commandParser);
            System.out.println("Сервер запущен!");

            while (true) {
                Socket clientSocket = server.accept();
                new Thread(() -> messageHandler.handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}