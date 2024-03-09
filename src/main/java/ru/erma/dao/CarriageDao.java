package ru.erma.dao;

import ru.erma.config.DBConnectionProvider;
import ru.erma.model.carriage.*;
import ru.erma.util.CarriageFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarriageDao {
    private final DBConnectionProvider connectionProvider;
    private final CarriageFactory carriageFactory;

    public CarriageDao(DBConnectionProvider connectionProvider, CarriageFactory carriageFactory) {
        this.connectionProvider = connectionProvider;
        this.carriageFactory = carriageFactory;
    }

    public void save(Carriage carriage, int trainId) {
        String sql = "INSERT INTO Carriage (type, comfortLevel, luggage_capacity, passenger_capacity, train_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, carriage.getType());
            preparedStatement.setInt(2, carriage.getComfortLevel());
            preparedStatement.setInt(3, carriage.getLuggageCapacity());
            preparedStatement.setInt(4, carriage.getPassengerCapacity());
            preparedStatement.setInt(5, trainId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Carriage> getCarriagesByTrainId(int trainId) {
        String sql = "SELECT * FROM Carriage WHERE train_id = ?";
        List<Carriage> carriages = new ArrayList<>();
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, trainId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Carriage carriage = createCarriageFromResultSet(resultSet);
                carriages.add(carriage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carriages;
    }

    public void updateComfortLevelByType(String type, int newComfortLevel) {
        String sql = "UPDATE Carriage SET comfortLevel = ? WHERE type = ?";

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, newComfortLevel);
            preparedStatement.setString(2, type);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Carriage createCarriageFromResultSet(ResultSet resultSet) throws SQLException {
        String type = resultSet.getString("type");
        int comfortLevel = resultSet.getInt("comfortLevel");
        int luggageCapacity = resultSet.getInt("luggage_capacity");
        int passengerCapacity = resultSet.getInt("passenger_capacity");

        return carriageFactory.createCarriage(type, comfortLevel, luggageCapacity, passengerCapacity);
    }
}