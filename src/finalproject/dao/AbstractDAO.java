package finalproject.dao;


import finalproject.dao.connection_manager.ConnectionManager;

import java.util.ResourceBundle;

/**
 * AbstactDAO which are iherited another classes
 *
 * @author Andrew
 * @version 1.1  29/07/2016
 */

public abstract class AbstractDAO {

    private ConnectionManager connectionManager;
    private ResourceBundle resourceBundle;

    public AbstractDAO(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.resourceBundle = ResourceBundle.getBundle("finalproject/properties/queries");
    }

    /**
     * get manger of connections
     *
     * @return object of connection manager
     */

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    /**
     * get object of resource bundle
     *
     * @return object of resouse bundle
     */

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

}
