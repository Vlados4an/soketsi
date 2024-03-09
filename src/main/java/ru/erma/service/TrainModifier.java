package ru.erma.service;

import ru.erma.dao.CarriageDao;
import ru.erma.dao.TrainDao;

public class TrainModifier {
    private final TrainDao trainDao;
    private final CarriageDao carriageDao;

    public TrainModifier(TrainDao trainDao, CarriageDao carriageDao) {
        this.trainDao = trainDao;
        this.carriageDao = carriageDao;
    }

    public String modifyTrainName(int id, String newTrainName) {
        trainDao.update(id,newTrainName);
        return "Train modified successfully";
    }

    public String modifyCarriageComfortLevel(String type, int newComfortLevel) {
        carriageDao.updateComfortLevelByType(type, newComfortLevel);
        return "Carriage comfort level modified successfully";
    }
}