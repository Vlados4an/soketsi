
package ru.erma.socket.handler;


import java.io.BufferedReader;
import java.io.IOException;

public class ClientCommandHandler {
    private final BufferedReader reader;

    public ClientCommandHandler(BufferedReader reader) {
        this.reader = reader;
    }

    public String handleCommand() throws IOException {
        System.out.println("Введите номер команды или 'exit' для выхода:");
        String command = reader.readLine();
        if ("exit".equalsIgnoreCase(command)) {
            return command;
        }
        String prompt = getPromptForCommand(command);
        if (prompt != null) {
            String userInput = getUserInput(prompt);
            command += " " + userInput;
        }
        if (command.charAt(0) == '5') {
            System.out.println("Введите минимальную вместимость:");
            String minCapacity = reader.readLine();
            System.out.println("Введите максимальную вместимость:");
            String maxCapacity = reader.readLine();
            command += " " + minCapacity + " " + maxCapacity;
        }
        return command;
    }

    private String getPromptForCommand(String command) {
        return switch (command) {
            case "1" -> "Введите имя поезда:";
            case "2", "3", "4", "5" -> "Введите TrainId:";
            default -> null;
        };
    }

    private String getUserInput(String prompt) throws IOException {
        System.out.println(prompt);
        return reader.readLine();
    }
}