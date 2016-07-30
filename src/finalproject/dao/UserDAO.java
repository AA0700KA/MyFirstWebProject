package finalproject.dao;


import finalproject.dao.connection_manager.ConnectionManager;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.User;
import finalproject.entity.users.UserFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class extends abstract dao {@link AbstractDAO}
 *
 *
 * @return user by login
 */

public class UserDAO extends AbstractDAO {

    private final Logger LOGGER = Logger.getLogger(UserDAO.class);

    public UserDAO(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    /**
     * Get authorized user {@link User}
     *
     * @param login
     *            by login
     *
     * @return user by login
     */

    public User getUser(String login) {
        try (Connection connection = getConnectionManager().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResourceBundle().getString("getAuthorizeUser"))) {
            preparedStatement.setString(1,login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String loginUser = resultSet.getString(2);
                String passwordUser = resultSet.getString(3);
                String name = resultSet.getString(4);
                boolean isAdmin = resultSet.getBoolean(5);
                int balance = resultSet.getInt(6);
                boolean blocked = resultSet.getBoolean(7);
                User user = UserFactory.getUser(isAdmin, balance, blocked);
                user.setId(id);
                user.setLogin(loginUser);
                user.setPassword(passwordUser);
                user.setName(name);

                LOGGER.info("getUser " + user.getLogin() + " succsessfull");
                return user;
            }

        } catch (SQLException e) {
            LOGGER.error("getUser " + login, e);
        }


        return null;

    }

    public void addUser(User user) {
        try(Connection connection = getConnectionManager().getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(login, password, name) VALUES(?,?,?)")) {

            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getName());

            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("addUser " + user.getLogin(), e);
        }

        LOGGER.info("addUser succsessfull");
    }

    /**
     * Get list users
     *
     *
     * @return list of not admin users
     */

    public List<User> getNoAdminUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getResourceBundle().getString("getNoAdminUsers"))) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String name = resultSet.getString(3);
                int balance = resultSet.getInt(4);
                boolean blocked = resultSet.getBoolean(5);
                Abonent user = new Abonent();
                user.setId(id);
                user.setLogin(login);
                user.setName(name);
                user.setBalance(balance);
                user.setBlocked(blocked);
                users.add(user);
            }

        } catch (SQLException e) {
            LOGGER.error("getUsers ", e);
        }

        LOGGER.info("getNoAdminUsers succsessfull");
        return users;
    }

    /**
     * upadte user balance
     *
     * @param user
     *            by user id {@link User}
     * @param value
     *            value added to user balance
     *
     */

    public void updateBalance(User user, int value) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET balance=balance+?, balance_rus=balance_rus+? WHERE id=?")) {

            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, value*15);
            preparedStatement.setInt(3, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("updateBalance " + user.getLogin() + " +" + value, e);
        }

        LOGGER.info("upadateBalance " + user.getLogin() + " value: " + value + " succsessfull");
    }

    /**
     * Block or unblock user
     *
     * @param id
     *            user id
     * @param blockStatus
     *          if block status 0 - unblock user, 1 - block user
     *
     */

    public void blockUserById(int id, int blockStatus) {
        try (Connection connection = getConnectionManager().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET blocked=? WHERE id=?")){
            preparedStatement.setInt(1, blockStatus);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("blockUserById " + id + " block status " + blockStatus, e);
        }

        LOGGER.info("Block user/Unblock user " + id + " " + blockStatus + " succsessfull");
    }

}
