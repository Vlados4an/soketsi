package ru.erma.util;

import java.io.BufferedReader;
import java.io.IOException;

public class InputHandler {
    private final BufferedReader reader;

    public InputHandler(BufferedReader reader) {
        this.reader = reader;
    }


    public String getStringInput(String message) throws IOException {
        System.out.println(message);
        return reader.readLine();
    }

    public int getIntInput(String message) throws IOException {
        System.out.println(message);
        while (true) {
            String line = reader.readLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("That's not a number! Please enter a number:");
            }
        }
    }

    public int getPositiveIntInput(String message) throws IOException {
        int input;
        do {
            input = getIntInput(message);
        } while (input <= 0);
        return input;
    }
}