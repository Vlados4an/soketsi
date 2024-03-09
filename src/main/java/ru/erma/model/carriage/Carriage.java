package ru.erma.model.carriage;

import java.util.Objects;

public abstract class Carriage {
    private final int comfortLevel;

    public Carriage(int comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

    public abstract String getType();
    public int getComfortLevel() {
        return comfortLevel;
    }
    public abstract int getPassengerCapacity();

    public abstract int getLuggageCapacity();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carriage carriage = (Carriage) o;
        return comfortLevel == carriage.comfortLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comfortLevel);
    }

    @Override
    public String toString() {
        return "Carriage{" +
                "comfortLevel=" + comfortLevel +
                '}';
    }
}