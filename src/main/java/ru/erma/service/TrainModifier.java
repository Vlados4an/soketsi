package ru.erma.service;

import ru.erma.dao.CarriageDao;
import ru.erma.dao.TrainDao;
import ru.erma.util.InputHandler;

public class TrainModifier {
    private final TrainDao trainDao;
    private final CarriageDao carriageDao;
    private final InputHandler inputHandler;

    public TrainModifier(TrainDao trainDao, CarriageDao carriageDao, InputHandler inputHandler) {
        this.trainDao = trainDao;
        this.carriageDao = carriageDao;
        this.inputHandler = inputHandler;
    }

    public void modifyTrainName(int id) {
        inputHandler.skipLine();
        String newTrainName = inputHandler.getStringInput("Enter new train name:");
        trainDao.update(id,newTrainName);
    }

    public void modifyCarriageComfortLevel(String type) {
        int newComfortLevel = inputHandler.getPositiveIntInput("Enter new comfort level for carriages of type " + type + ":");
        carriageDao.updateComfortLevelByType(type, newComfortLevel);
    }
}