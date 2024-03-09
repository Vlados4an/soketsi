package ru.erma.socket;

import java.io.*;
import java.net.Socket;

public class ClientReceiver {

    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 4004);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            out.write("ClientReceiver\n");
            out.flush();

            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println(serverMessage);
            }

            clientSocket.close();
            in.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}