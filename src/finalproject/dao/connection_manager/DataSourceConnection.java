package finalproject.dao.connection_manager;


import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSourceConnection get connection from Apache Tomcat data source pool
 * Class impliments {@link ConnectionManager}
 *
 * @author Andrew
 * @version 1.0  30/07/2016
 */

public class DataSourceConnection implements ConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(DataSourceConnection.class);

    private static DataSource dataSource;

    static {
        try {
            InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/abonent");
            LOGGER.info("Init data source!!!");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() throws SQLException {
        LOGGER.info("Data Source!!!");
        return dataSource.getConnection();
    }

}
