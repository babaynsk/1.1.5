package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private  final String URL = "jdbc:mysql://localhost:3306/myFirstLesson";
    private  final String USERNAME = "root";
    private  final String PASSWORD = "root";
    // реализуйте настройку соеденения с БД

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection is work");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
