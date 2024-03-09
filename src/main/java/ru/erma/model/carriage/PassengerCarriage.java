package ru.erma.model.carriage;

import java.util.Objects;

public class PassengerCarriage extends Carriage {
    private final int passengerCapacity;
    private final int luggageCapacity;

    public PassengerCarriage(int comfortLevel, int passengerCapacity, int luggageCapacity) {
        super(comfortLevel);
        this.passengerCapacity = passengerCapacity;
        this.luggageCapacity = luggageCapacity;
    }
    @Override
    public String getType() {
        return "Passenger";
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public int getLuggageCapacity() {
        return luggageCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerCarriage that = (PassengerCarriage) o;
        return passengerCapacity == that.passengerCapacity && luggageCapacity == that.luggageCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerCapacity, luggageCapacity);
    }

    @Override
    public String toString() {
        return "PassengerCarriage";
    }
}