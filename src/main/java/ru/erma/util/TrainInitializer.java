package ru.erma.util;

import ru.erma.dao.CarriageDao;
import ru.erma.dao.TrainDao;
import ru.erma.model.Train;
import ru.erma.model.carriage.*;

public class TrainInitializer {
    private final TrainDao trainDao;
    private final CarriageDao carriageDao;

    public TrainInitializer(TrainDao trainDao, CarriageDao carriageDao) {
        this.trainDao = trainDao;
        this.carriageDao = carriageDao;
    }

    public String initializeTrain(String trainName) {
        Train train = new Train(trainName);
        int trainId = trainDao.save(train);
        train.setId(trainId);

        PassengerCarriage passengerCarriage1 = new PassengerCarriage(1, 50, 20);
        FreightCarriage freightCarriage1 = new FreightCarriage(1, 1000);
        DiningCarriage diningCarriage1 = new DiningCarriage(2, 30);
        SleepingCarriage sleepingCarriage1 = new SleepingCarriage(3, 20);

        train.addCarriage(passengerCarriage1);
        train.addCarriage(freightCarriage1);
        train.addCarriage(diningCarriage1);
        train.addCarriage(sleepingCarriage1);

        for (Carriage carriage : train.getCarriages()) {
            carriageDao.save(carriage, trainId);
        }

        return "Train saved successfully";
    }
}