package finalproject.dao.connection_manager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JdbcConnection get connection from jdbc driver
 * Class impliments {@link ConnectionManager}
 *
 * @author Andrew
 * @version 1.0  30/07/2016
 */

public class JdbcConnection implements ConnectionManager {

    private final Logger LOGGER = Logger.getLogger(JdbcConnection.class);

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() throws SQLException {
        LOGGER.info("Test connection!!! ");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/abonent_schema", "root", "root");
    }

}
