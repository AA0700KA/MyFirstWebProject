package finalproject.dao;

import finalproject.dao.connection_manager.ConnectionManager;
import finalproject.entity.Payment;
import finalproject.entity.services.Service;
import finalproject.entity.services.ServiceFactory;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.User;
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
 * @author Andrew
 * @version 1.1 29/07/2016
 */

public class PaymentDAO extends AbstractDAO {

    private final Logger LOGGER = Logger.getLogger(PaymentDAO.class);

    public PaymentDAO(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    /**
     * add payment
     *
     * @param user
     *            by user {@link User}
     * @param service
     *             service {@link Service}
     *
     */

    public void addPayment(User user, Service service) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO payments(user_id, service_id, price, price_rus) VALUES(?,?,?,?)")) {
             preparedStatement.setInt(1, user.getId());
             preparedStatement.setInt(2, service.getId());
             preparedStatement.setInt(3, service.getPrice()/15);
             preparedStatement.setInt(4, service.getPrice());

            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("addPayment " + user.getLogin() + " " + service.getName(), e);
        }

        LOGGER.info("addPayment " + user.getLogin() + " " + service.getName() + " succsessfull");
    }

    /**
     * Get all payments from data base
     *
     * @return list of payments
     */

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = getConnectionManager().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResourceBundle().getString("getPayments")))
             {
                 ResultSet resultSet = preparedStatement.executeQuery();
                 initPayments(resultSet,payments);

        } catch (SQLException e) {
            LOGGER.error("getAllPayments", e);
        }

        LOGGER.info("getAllPayments succsessfull!");
        return payments;
    }

    /**
     * Get paid or unpaid payments
     *
     * @param paidStatus
     *                 status:
     *                       true - paid payments
     *                       false - ubpaid payments
     *
     *
     * @return list of paid or unpaid payments
     */

    public List<Payment> getPaymentsFilter(boolean paidStatus) {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = getConnectionManager().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResourceBundle().getString("getPaymentsFilter")))
        {
            preparedStatement.setBoolean(1, paidStatus);
            ResultSet resultSet = preparedStatement.executeQuery();
            initPayments(resultSet,payments);

        } catch (SQLException e) {
            LOGGER.error("getPayments " + paidStatus, e);
        }

        LOGGER.info("getPayments" + paidStatus + " succsessfull");
        return payments;
    }

    /**
     * Get payments by
     *
     * @param user
     *            current user {@link User}
     *
     * @return list of payments current users
     */

    public List<Payment> getAbonentPayments(User user) {
        List<Payment> payments = new ArrayList<>();
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getResourceBundle().getString("getPaymentsUser"))) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String serviceName = resultSet.getString(2);
                int price = resultSet.getInt(3);
                boolean paid = resultSet.getBoolean(4);
                Service service = ServiceFactory.getService(id);
                service.setName(serviceName);
                Payment payment = new Payment();
                payment.setService(service);
                payment.setPrice(price);
                payment.setPaid(paid);
                payment.setUser(user);
                payments.add(payment);
            }

        } catch (SQLException e) {
            LOGGER.error("getAbonentPayments " + user.getLogin(), e);
        }

        LOGGER.info("getAbonentPayments " + user.getLogin() + " succsessfull");

        return payments;
    }

    private void initPayments(ResultSet resultSet, List<Payment> payments) throws SQLException {

        while (resultSet.next()) {
            int userId = resultSet.getInt(1);
            String login = resultSet.getString(2);
            String userName = resultSet.getString(3);
            int serviceId = resultSet.getInt(4);
            String serviceName = resultSet.getString(5);
            int price = resultSet.getInt(6);
            boolean paidStatus = resultSet.getBoolean(7);
            Abonent abonent = new Abonent();
            abonent.setId(userId);
            abonent.setLogin(login);
            abonent.setName(userName);
            Service service = ServiceFactory.getService(serviceId);
            service.setName(serviceName);
            Payment payment = new Payment();
            payment.setUser(abonent);
            payment.setService(service);
            payment.setPrice(price);
            payment.setPaid(paidStatus);
            payments.add(payment);
        }

    }

    /**
     * pay service
     *
     * @param user
     *            by user id {@link User}
     * @param service
     *            by service id {@link Service}
     *
     */

    public void payService(User user, Service service) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE payments SET paid=1 WHERE user_id=? AND service_id=?")) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, service.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("pay service " + user.getLogin() + ": " + service.getName(), e);
        }

        LOGGER.info("payService " + user.getLogin() + " " + service.getName() + " succsessfull");
    }

    /**
     * remove service
     *
     * @param user
     *            by user id {@link User}
     * @param service
     *            by service id {@link Service}
     *
     */

    public void removeService(Service service, User user) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM payments WHERE service_id=? AND user_id=?")) {
            preparedStatement.setInt(1, service.getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("removeService " + user.getLogin() + " " + service.getName(), e);
        }

        LOGGER.info("remove service " + user.getLogin() + " " + service.getName() + " succsessfull");
    }

}
