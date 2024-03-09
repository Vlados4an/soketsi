package ru.erma.util;

public class Menu {
    private final InputHandler inputHandler;

    public Menu(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void displayMenu() {
        System.out.println("Choose an option:");
        System.out.println("1) Save new train");
        System.out.println("2) Display total passenger capacity");
        System.out.println("3) Display total luggage capacity");
        System.out.println("4) Sort carriages by comfort level");
        System.out.println("5) Find carriages by passenger capacity range");
        System.out.println("6) Modify train");
        System.out.println("7) Modify carriage");
        System.out.println("8) Exit");
    }

    private void displayCarriageTypes() {
        System.out.println("Choose a carriage type:");
        System.out.println("1) Dining");
        System.out.println("2) Freight");
        System.out.println("3) Passenger");
        System.out.println("4) Sleeping");
    }

    public int getUserChoice() {
        return inputHandler.getPositiveIntInput("Choose an option:");
    }

    public int getMinPassengerCapacity() {
        return inputHandler.getPositiveIntInput("Enter min passenger capacity:");
    }

    public int getMaxPassengerCapacity(int min) {
        int max;
        do {
            max = inputHandler.getPositiveIntInput("Enter max passenger capacity:");
            if (max < min) {
                System.out.println("Max passenger capacity should be greater than min passenger capacity. Please try again.");
            }
        } while (max < min);
        return max;
    }

    public int getTrainIdFromUser() {
        return inputHandler.getPositiveIntInput("Enter the ID of the train:");
    }

    public String getCarriageTypeFromUser() {
        displayCarriageTypes();
        int typeIndex;
        String[] types = {"Dining", "Freight", "Passenger", "Sleeping"};
        while (true) {
            typeIndex = inputHandler.getPositiveIntInput("Choose a carriage type:") - 1;
            if (typeIndex >= 0 && typeIndex < types.length) {
                break;
            } else {
                System.out.println("Invalid option. Please choose a number between 1 and " + types.length + ".");
            }
        }
        return types[typeIndex];
    }
}