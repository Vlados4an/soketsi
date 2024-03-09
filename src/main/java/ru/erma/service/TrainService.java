package ru.erma.service;

import ru.erma.dao.CarriageDao;
import ru.erma.model.carriage.Carriage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    private final CarriageDao carriageDao;

    public TrainService(CarriageDao carriageDao) {
        this.carriageDao = carriageDao;
    }

    public int getTotalPassengerCapacity(int trainId) {
        List<Carriage> carriages = carriageDao.getCarriagesByTrainId(trainId);
        return carriages.stream().mapToInt(Carriage::getPassengerCapacity).sum();
    }

    public int getTotalLuggageCapacity(int trainId) {
        List<Carriage> carriages = carriageDao.getCarriagesByTrainId(trainId);
        return carriages.stream().mapToInt(Carriage::getLuggageCapacity).sum();
    }

    public List<Carriage> sortCarriagesByComfortLevel(int trainId) {
        List<Carriage> carriages = carriageDao.getCarriagesByTrainId(trainId);
        carriages.sort(Comparator.comparingInt(Carriage::getComfortLevel));
        return carriages;
    }

    public List<Carriage> findCarriagesByPassengerCapacityRange(int trainId, int min, int max) {
        List<Carriage> carriages = carriageDao.getCarriagesByTrainId(trainId);
        return carriages.stream()
                .filter(carriage -> carriage.getPassengerCapacity() >= min && carriage.getPassengerCapacity() <= max)
                .collect(Collectors.toList());
    }
}