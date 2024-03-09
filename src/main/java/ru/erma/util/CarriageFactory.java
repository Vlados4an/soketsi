package ru.erma.util;

import ru.erma.model.carriage.*;

public class CarriageFactory {
    public Carriage createCarriage(String type, int comfortLevel, int luggageCapacity, int passengerCapacity) {
        return switch (type) {
            case "Passenger" -> new PassengerCarriage(comfortLevel, passengerCapacity, luggageCapacity);
            case "Freight" -> new FreightCarriage(comfortLevel, luggageCapacity);
            case "Dining" -> new DiningCarriage(comfortLevel, passengerCapacity);
            case "Sleeping" -> new SleepingCarriage(comfortLevel, passengerCapacity);
            default -> throw new IllegalArgumentException("Unknown carriage type: " + type);
        };
    }
}