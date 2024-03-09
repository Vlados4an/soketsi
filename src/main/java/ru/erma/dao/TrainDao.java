package ru.erma.dao;

import ru.erma.config.DBConnectionProvider;
import ru.erma.model.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainDao {
    private final DBConnectionProvider connectionProvider;

    public TrainDao(DBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public int save(Train train) {
        String sql = "INSERT INTO Train (name) VALUES (?) RETURNING id";

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, train.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void update(int id, String name) {
        String sql = "UPDATE Train SET name = ? WHERE id = ?";

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}