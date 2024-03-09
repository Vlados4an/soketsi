package ru.erma.socket.handler;

import ru.erma.util.Application;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServerMessageHandler {
    private static final Map<String, BufferedWriter> clients = new HashMap<>();
    private final Application app;

    private final CommandParser commandParser;

    public ServerMessageHandler(Application app, CommandParser commandParser) {
        this.app = app;
        this.commandParser = commandParser;
    }

    public void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String clientName = in.readLine();
            clients.put(clientName, out);

            processClientMessages(in, out);

            clients.remove(clientName);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processClientMessages(BufferedReader in, BufferedWriter out) throws IOException {
        String message;
        while ((message = in.readLine()) != null) {
            processMessage(message, out);
        }
    }

    private void processMessage(String message, BufferedWriter out) throws IOException {
        Command command = commandParser.parse(message);
        String output;
        if ("5".equals(command.command()) || "6".equals(command.command()) || "7".equals(command.command())) {
            output = app.runCommandWithRange(command.command(), command.params());
        } else {
            output = app.runCommand(command.command(), Arrays.toString(command.params()));
        }

        sendOutputToClient(output, out);
    }

    private void sendOutputToClient(String output, BufferedWriter out) throws IOException {
        BufferedWriter targetOut = clients.get("ClientReceiver");
        if (targetOut != null) {
            targetOut.write(output + "\n");
            targetOut.flush();
        }
    }
}