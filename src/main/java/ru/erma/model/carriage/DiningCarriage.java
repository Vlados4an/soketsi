package ru.erma.model.carriage;

import java.util.Objects;

public class DiningCarriage extends Carriage {
    private final int seatCapacity;

    public DiningCarriage(int comfortLevel, int seatCapacity) {
        super(comfortLevel);
        this.seatCapacity = seatCapacity;
    }

    @Override
    public String getType() {
        return "Dining";
    }

    @Override
    public int getPassengerCapacity() {
        return seatCapacity;
    }

    @Override
    public int getLuggageCapacity() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiningCarriage that = (DiningCarriage) o;
        return seatCapacity == that.seatCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), seatCapacity);
    }

    @Override
    public String toString() {
        return "DiningCarriage";
    }
}