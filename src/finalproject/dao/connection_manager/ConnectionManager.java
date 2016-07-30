package finalproject.dao.connection_manager;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * ConnectionManager that return connection dependents programm mode
 *
 * @author Andrew
 * @version 1.0  30/07/2016
 */

public interface ConnectionManager {

    /**
     *
     * @return Connection
     *                   dependents programm mode: JUnit test or Apache Tomcat DataSource
     * @throws SQLException
     */

    Connection getConnection() throws SQLException;

}
