package finalproject.dao;


import finalproject.dao.connection_manager.ConnectionManager;
import finalproject.dao.connection_manager.DataSourceConnection;
import finalproject.dao.connection_manager.JdbcConnection;

/**
 * DAOFactory get DAO entities
 *
 * @author Andrew
 * @version 1.0  23/07/2016
 */

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private ConnectionManager connectionManager;

    private DAOFactory() {

    }

    public synchronized static DAOFactory getInstance() {
        return instance;
    }

    /**
     * Create object of user DAO{@link UserDAO}
     *
     * @return object of user DAO
     */

    public UserDAO getUserDAO() {
        return new UserDAO(connectionManager);
    }

    /**
     * Create object of service DAO{@link ServiceDAO}
     *
     * @return object of service DAO
     */

    public ServiceDAO getServiceDAO() {
        return new ServiceDAO(connectionManager);
    }

    /**
     * Create object of payment DAO{@link PaymentDAO}
     *
     * @return object of payment DAO
     */

    public PaymentDAO getPaymentDAO() {
        return new PaymentDAO(connectionManager);
    }

    /**
     * Create state of connection maneger{@link ConnectionManager}
     */

    public void setTest(boolean isTest) {
        if (!isTest) {
            connectionManager = new DataSourceConnection();
        } else {
            connectionManager = new JdbcConnection();
        }
    }

}
