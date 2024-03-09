package ru.erma.util;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getStringInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public int getIntInput(String message) {
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a number! Please enter a number:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void skipLine() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    public int getPositiveIntInput(String message) {
        int input;
        do {
            input = getIntInput(message);
        } while (input <= 0);
        return input;
    }
}