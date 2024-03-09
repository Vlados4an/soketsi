package ru.erma.model.carriage;

import java.util.Objects;

public class FreightCarriage extends Carriage {
    private final int freightCapacity;

    public FreightCarriage(int comfortLevel, int freightCapacity) {
        super(comfortLevel);
        this.freightCapacity = freightCapacity;
    }

    @Override
    public String getType() {
        return "Freight";
    }
    @Override
    public int getPassengerCapacity() {
        return 0; // Freight carriage does not carry passengers
    }

    @Override
    public int getLuggageCapacity() {
        return freightCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreightCarriage that = (FreightCarriage) o;
        return freightCapacity == that.freightCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(freightCapacity);
    }

    @Override
    public String toString() {
        return "FreightCarriage";
    }
}