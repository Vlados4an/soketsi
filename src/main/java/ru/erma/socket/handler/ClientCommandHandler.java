package ru.erma.socket.handler;

import ru.erma.util.InputHandler;
import ru.erma.util.Menu;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientCommandHandler {
    private final InputHandler inputHandler;
    private final Menu menu;

    public ClientCommandHandler(BufferedReader reader) {
        this.inputHandler = new InputHandler(reader);
        this.menu = new Menu(inputHandler);
    }

    public String handleCommand() throws IOException {
        menu.displayMenu();
        int commandNumber = menu.getUserChoice();
        if (commandNumber == 8) {
            return "exit";
        }
        String command = String.valueOf(commandNumber);
        String prompt = getPromptForCommand(command);
        if (prompt == null){
            return null;
        }
        if (prompt.startsWith("1") || prompt.startsWith("7")) {
            String userInput = inputHandler.getStringInput(prompt);
            command += " " + userInput;
        } else {
            int trainId = menu.getTrainIdFromUser();
            command += " " + trainId;
        }
        if (command.startsWith("5")) {
            int minCapacity = menu.getMinPassengerCapacity();
            int maxCapacity = menu.getMaxPassengerCapacity(minCapacity);
            command += " " + minCapacity + " " + maxCapacity;
        } else if (command.startsWith("6")) {
            String trainName = inputHandler.getStringInput("Введите новое имя поезда:");
            command += " " + trainName;
        } else if (command.startsWith("7")){
            String type = menu.getCarriageTypeFromUser();
            command += " " + type;
        }
        return command;
    }

    private String getPromptForCommand(String command) {
        return switch (command) {
            case "1" -> "Введите имя поезда:";
            case "2", "3", "4", "5", "6" -> "Введите TrainId:";
            case "7" -> "Введите тип вагона для изменения";
            default -> null;
        };
    }
}