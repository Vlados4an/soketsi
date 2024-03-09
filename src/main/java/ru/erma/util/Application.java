package ru.erma.util;

import ru.erma.config.DBConnectionProvider;
import ru.erma.config.DatabaseConfiguration;
import ru.erma.dao.CarriageDao;
import ru.erma.dao.TrainDao;
import ru.erma.model.carriage.Carriage;
import ru.erma.service.TrainService;
import ru.erma.service.TrainModifier;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private final TrainService trainService;
    private final TrainInitializer trainInitializer;
    private final TrainModifier trainModifier;

    public Application(TrainService trainService, TrainInitializer trainInitializer, TrainModifier trainModifier) {
        this.trainService = trainService;
        this.trainInitializer = trainInitializer;
        this.trainModifier = trainModifier;
    }

    public String runCommand(String command,String trainParam) {
        trainParam = trainParam.replaceAll("[^0-9]", "");
        return switch (command) {
            case "1" -> saveTrain(trainParam);
            case "2" -> displayTotalPassengerCapacity(Integer.parseInt(trainParam));
            case "3" -> displayTotalLuggageCapacity(Integer.parseInt(trainParam));
            case "4" -> sortCarriagesByComfortLevel(Integer.parseInt(trainParam));

            default -> invalidOption();
        };
    }



    public String runCommandWithRange(String command, String[] params) {
        return switch (command){
            case "5" -> {
                int trainId = Integer.parseInt(params[0]);
                int minCapacity = Integer.parseInt(params[1]);
                int maxCapacity = Integer.parseInt(params[2]);
                yield findCarriagesByPassengerCapacityRange(trainId, minCapacity, maxCapacity);
            }
            case "6" -> {
                int trainId = Integer.parseInt(params[0]);
                String trainName = params[1];
                yield modifyTrain(trainId, trainName);
            }
            case "7" -> {
                String type = params[0];
                int comfortLevel = Integer.parseInt(params[1]);
                yield modifyCarriage(type, comfortLevel);
            }

            default -> invalidOption();
        };
    }

    public static Application runApp(){
        DatabaseConfiguration.loadProperties();
        DBConnectionProvider connectionProvider = DatabaseConfiguration.connectionProviderConfiguration();
        CarriageFactory carriageFactory = new CarriageFactory();
        CarriageDao carriageDao = new CarriageDao(connectionProvider, carriageFactory);
        TrainDao trainDao = new TrainDao(connectionProvider);
        TrainInitializer trainInitializer = new TrainInitializer(trainDao, carriageDao);
        TrainService trainService = new TrainService(carriageDao);
        TrainModifier trainModifier = new TrainModifier(trainDao, carriageDao);
        return new Application(trainService, trainInitializer, trainModifier);
    }

    private String saveTrain(String trainName) {
        return trainInitializer.initializeTrain(trainName);
    }

    private String displayTotalPassengerCapacity(int trainId) {
        return "Total passenger capacity: " + trainService.getTotalPassengerCapacity(trainId);
    }

    private String displayTotalLuggageCapacity(int trainId) {
        return "Total luggage capacity: " + trainService.getTotalLuggageCapacity(trainId);
    }

    private String sortCarriagesByComfortLevel(int trainId) {
        List<Carriage> carriages = trainService.sortCarriagesByComfortLevel(trainId);
        return carriages.stream()
                .map(Carriage::toString)
                .collect(Collectors.joining("\n"));
    }

    private String findCarriagesByPassengerCapacityRange(int trainId, int min, int max) {
        List<Carriage> carriagesInRange = trainService.findCarriagesByPassengerCapacityRange(trainId, min, max);
        return carriagesInRange.stream()
                .map(Carriage::toString)
                .collect(Collectors.joining("\n"));
    }

    private String modifyTrain(int trainId, String trainName) {
        return trainModifier.modifyTrainName(trainId, trainName);
    }

    private String modifyCarriage(String type, int newComfortLevel) {
        return trainModifier.modifyCarriageComfortLevel(type, newComfortLevel);
    }

    private String invalidOption() {
        return "Invalid option. Please try again.";
    }
}