// ClientSender.java
package ru.erma.socket;


import ru.erma.socket.handler.ClientCommandHandler;


import java.io.*;
import java.net.Socket;

public class ClientSender {
    public static void main(String[] args) throws InterruptedException {
        try {
            Socket clientSocket = new Socket("localhost", 4004);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            ClientCommandHandler commandHandler = new ClientCommandHandler(reader);

            String command;
            while (true) {
                command = commandHandler.handleCommand();
                if ("exit".equalsIgnoreCase(command)) {
                    break;
                }
                out.write(command + "\n");
                out.flush();
            }

            clientSocket.close();
            out.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}