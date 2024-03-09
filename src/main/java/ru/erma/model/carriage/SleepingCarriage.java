package ru.erma.model.carriage;

import java.util.Objects;

public class SleepingCarriage extends Carriage {
    private final int bedCapacity;

    public SleepingCarriage(int comfortLevel, int bedCapacity) {
        super(comfortLevel);
        this.bedCapacity = bedCapacity;
    }

    @Override
    public String getType() {
        return "Sleeping";
    }

    @Override
    public int getPassengerCapacity() {
        return bedCapacity;
    }

    @Override
    public int getLuggageCapacity() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SleepingCarriage that = (SleepingCarriage) o;
        return bedCapacity == that.bedCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bedCapacity);
    }

    @Override
    public String toString() {
        return "SleepingCarriage";
    }
}