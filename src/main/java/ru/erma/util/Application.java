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
    private final Menu menu;

    public Application(TrainService trainService, TrainInitializer trainInitializer, TrainModifier trainModifier, Menu menu) {
        this.trainService = trainService;
        this.trainInitializer = trainInitializer;
        this.trainModifier = trainModifier;
        this.menu = menu;
    }

    public String runCommand(String command,String trainParam) {
        trainParam = trainParam.replaceAll("[^0-9]", ""); // Remove all non-digit characters
        return switch (command) {
            case "1" -> saveTrain(trainParam);
            case "2" -> displayTotalPassengerCapacity(Integer.parseInt(trainParam));
            case "3" -> displayTotalLuggageCapacity(Integer.parseInt(trainParam));
            case "4" -> sortCarriagesByComfortLevel(Integer.parseInt(trainParam));

            default -> invalidOption();
        };
    }

    public String runCommandWithRange(String command, String[] params) {
        int trainId = Integer.parseInt(params[0]);
        int minCapacity = Integer.parseInt(params[1]);
        int maxCapacity = Integer.parseInt(params[2]);
        return findCarriagesByPassengerCapacityRange(trainId, minCapacity, maxCapacity);
    }

    public static Application runApp(){
        DatabaseConfiguration.loadProperties();
        DBConnectionProvider connectionProvider = DatabaseConfiguration.connectionProviderConfiguration();
        CarriageFactory carriageFactory = new CarriageFactory();
        CarriageDao carriageDao = new CarriageDao(connectionProvider, carriageFactory);
        TrainDao trainDao = new TrainDao(connectionProvider);
        InputHandler inputHandler = new InputHandler();
        TrainInitializer trainInitializer = new TrainInitializer(trainDao, carriageDao);
        TrainService trainService = new TrainService(carriageDao);
        TrainModifier trainModifier = new TrainModifier(trainDao, carriageDao, inputHandler);
        Menu menu = new Menu(inputHandler);
        return new Application(trainService, trainInitializer, trainModifier, menu);
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

    private void modifyTrain() {
        int id = menu.getTrainIdFromUser();
        trainModifier.modifyTrainName(id);
    }

    private void modifyCarriage() {
        String type = menu.getCarriageTypeFromUser();
        trainModifier.modifyCarriageComfortLevel(type);
    }

    private String invalidOption() {
        return "Invalid option. Please try again.";
    }
}