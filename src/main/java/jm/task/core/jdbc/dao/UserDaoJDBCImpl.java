package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connect = getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS users (id INT,firstName VARCHAR(255),lastName VARCHAR(255),age INT)";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        try  {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (connect != null) {
                connect.close();
            }
        }

    }

    public void dropUsersTable() throws SQLException {
        Connection connect = getConnection();

        String sql = "DROP TABLE IF EXISTS users";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (connect != null) {
                connect.close();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connect = getConnection();

        String sql = "INSERT INTO users (FIRSTNAME ,LASTNAME ,AGE) VALUES(? ,? ,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        try  {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (connect != null) {
                connect.close();
            }
        }

    }

    public void removeUserById(long id) throws SQLException {
        Connection connect = getConnection();

        String sql = "DELETE FROM users WHERE ID = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);
        try  {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (connect != null) {
                connect.close();
            }

        }
    }

    public List<User> getAllUsers() throws SQLException {
        Connection connect = getConnection();
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT ID, FIRSTNAME, LASTNAME, AGE FROM users";

        try (Statement statement = connect.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (connect != null) {
                connect.close();
            }
        }

        return usersList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connect = getConnection();

        String sql = "DELETE FROM users";
        PreparedStatement preparedStatement = connect.prepareStatement(sql);

        try  {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (connect != null) {
                connect.close();
            }
        }
    }
}
