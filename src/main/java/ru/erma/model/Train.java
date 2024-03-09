package ru.erma.model;

import ru.erma.model.carriage.Carriage;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private int id;
    private final List<Carriage> carriages;
    private final String name;

    public Train(String name) {
        this.carriages = new ArrayList<>();
        this.name = name;
    }

    public void addCarriage(Carriage carriage) {
        carriages.add(carriage);
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}