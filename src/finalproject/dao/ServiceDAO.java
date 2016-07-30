package finalproject.dao;


import finalproject.dao.connection_manager.ConnectionManager;
import finalproject.entity.services.*;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.User;
import finalproject.exceptions.NotCurrentServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Class extends {@link AbstractDAO}
 *
 * @author Andrew
 * @version 1.2 27/07/2016
 */

public class ServiceDAO extends AbstractDAO {

    private final Logger LOGGER = Logger.getLogger(ServiceDAO.class);

    public ServiceDAO(ConnectionManager connectionManager) {
        super(connectionManager);
    }

    /**
     * Get all rervices from data base
     *
     * @return list of services
     *
     */

    public List<Service> getServices() {
        List<Service> services = new ArrayList<>();
        try (Connection connection = getConnectionManager().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResourceBundle().getString("getServices"))) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Service service = ServiceFactory.getService(id);
                service.setName(name);
                services.add(service);
            }

        } catch (SQLException e) {
            LOGGER.error("getAll services ", e);
        }

        LOGGER.info("getAll services succsessfull");

        return services;
    }

    /**
     * Get services map
     *
     * @param user
     *            by user id {@link User}
     *
     * @return map of services key - table id, value service
     */

    public Map<Integer, Service> getUserServices(User user) {
        if (user instanceof Abonent) {
            Map<Integer, Service> serviceMap = new HashMap<>();

            try(Connection connection = getConnectionManager().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT s.id, s.service FROM services s INNER JOIN payments p ON p.service_id=s.id INNER JOIN users u ON p.user_id = u.id WHERE u.id=?")) {
                preparedStatement.setInt(1, user.getId());

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    Service service = ServiceFactory.getService(id);
                    service.setName(name);

                    if (service instanceof Internet) {
                        Internet currentNet = getInternet(user);
                        ((Internet)service).setSpeed(currentNet.getSpeed());
                    } else if (service instanceof Television) {
                        Television currentTV = getTelevision(user);
                        ((Television)service).setCountChanels(currentTV.getCountChanels());
                    } else if (service instanceof IPTelefony) {
                        IPTelefony currentIPtelefony = getIPTelefony(user);
                        ((IPTelefony)service).setVideoCall(currentIPtelefony.isVideoCall());
                        ((IPTelefony)service).setRoumingMinutes(currentIPtelefony.getRoumingMinutes());
                    }

                    serviceMap.put(id, service);
                }

            } catch (SQLException e) {
                LOGGER.error("UserServices " + user.getLogin(), e);
            }

            LOGGER.info("getServicesMap " + user.getLogin() + " succsessfull");
            return  serviceMap;
        }
        return null;
    }

    /**
     * get internet {@link Internet}
     *
     * @param user
     *            by user id {@link User}
     *
     * @return internet characteristic by user id
     *
     */

    public Internet getInternet(User user) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT speed FROM internet_characteristic WHERE user_id=?")) {
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Internet internet = new Internet();
                int speed = resultSet.getInt(1);
                internet.setSpeed(speed);
                return internet;
            }

        } catch (SQLException e) {
            LOGGER.error("getInternet " + user.getLogin(), e);
        }

        return null;
    }

    /**
     * get television {@link Television}
     *
     * @param user
     *            by user id {@link User}
     *
     * @return television characteristic by user id
     *
     */

    public Television getTelevision(User user) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT count_chanels FROM television_characteristic WHERE user_id=?")) {
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Television television = new Television();
                int count_chanels = resultSet.getInt(1);
                television.setCountChanels(count_chanels);
                return television;
            }

        } catch (SQLException e) {
            LOGGER.error("getTekevision " + user.getLogin(), e);
        }

        return null;
    }

    /**
     * get ip-telefony {@link IPTelefony}
     *
     * @param user
     *            by user id {@link User}
     *
     * @return op-telefony characteristic by user id
     *
     */

    public IPTelefony getIPTelefony(User user) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT minutes_in_rouming, videocall FROM ip_telefony_characteristic WHERE user_id=?")) {
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                IPTelefony ipTelefony = new IPTelefony();
                int minutesInRouming = resultSet.getInt(1);
                boolean videocall = resultSet.getBoolean(2);
                ipTelefony.setRoumingMinutes(minutesInRouming);
                ipTelefony.setVideoCall(videocall);
                return ipTelefony;
            }

        } catch (SQLException e) {
            LOGGER.error("getIPTelefony " + user.getLogin(), e);
        }

        return null;
    }

    /**
     * add television characteristic
     *
     * @param user
     *            by user id {@link User}
     * @param service
     *            if service instance of television {@link Television}
     *
     *
     */

    public void addTV(Service service, User user) {
        if (service instanceof Television) {
            Television television = (Television) service;
            try(Connection connection = getConnectionManager().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO television_characteristic(user_id, count_chanels) VALUES(?,?)")) {
                preparedStatement.setInt(1,user.getId());
                preparedStatement.setInt(2, television.getCountChanels());

                preparedStatement.execute();
            } catch (SQLException e) {
                LOGGER.error("addTV " + user.getLogin() + " " + television.getCountChanels(), e);
            }

            LOGGER.info("addTV " + user.getLogin() + " succsessfull");
        } else {
            throw new NotCurrentServiceException(service.getName() + " isn't television");
        }
    }

    /**
     * add internet characteristic
     *
     * @param user
     *            by user id {@link User}
     * @param service
     *            if service instance of internet{@link Internet}
     *
     *
     */

    public void addInternet(Service service, User user) {
        if (service instanceof Internet) {
            Internet internet = (Internet) service;
            try(Connection connection = getConnectionManager().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO internet_characteristic(user_id, speed) VALUES(?,?)")) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, internet.getSpeed());

                preparedStatement.execute();
            } catch (SQLException e) {
                LOGGER.error("addInternet " + user.getLogin() + " " + internet.getSpeed(), e);
            }

            LOGGER.info("addInternet " + user.getLogin() + " succsessfull");
        } else {
            throw new NotCurrentServiceException(service.getName() + " isn't internet");
        }
    }

    /**
     * add ip-telefony characteristic
     *
     * @param user
     *            by user id {@link User}
     * @param service
     *            if service instance of ip-telefony{@link IPTelefony}
     *
     *
     */

    public void addIPTelefony(Service service, User user) {
        if (service instanceof IPTelefony) {
            IPTelefony telefony = (IPTelefony) service;
            try(Connection connection = getConnectionManager().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ip_telefony_characteristic(user_id, minutes_in_rouming, videocall) VALUES(?,?,?)")) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, telefony.getRoumingMinutes());
                preparedStatement.setBoolean(3, telefony.isVideoCall());

                preparedStatement.execute();
            } catch (SQLException e) {
                LOGGER.error("addIPTelefony " + user.getLogin() + " " + telefony.getRoumingMinutes() + " isVideocall " + telefony.isVideoCall(), e);
            }

            LOGGER.info("addIPTelefony " + user.getLogin() + " succsessfull");
        } else {
            throw new NotCurrentServiceException(service.getName() + " isn't ip-telefony");
        }
    }

    /**
     * remove service{@link Service}
     *
     * @param user
     *            by user id {@link User}
     * @param service
     *            by service id {@link Service}
     *
     *
     */

    public void remove(Service service, User user) {
        if (service instanceof Internet) {
            removeInternet(user);
        } else if (service instanceof IPTelefony) {
            removeIPtelefony(user);
        } else if (service instanceof Television) {
            removeTelevision(user);
        }
    }

    /**
     * remove internet{@link Internet}
     *
     * @param user
     *            by user id {@link User}
     *
     */

    private void removeInternet(User user) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM internet_characteristic WHERE user_id=?")) {
            preparedStatement.setInt(1, user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("removeInternet " + user.getLogin(), e);
        }

        LOGGER.info("removeInternet " + user.getLogin() + " succsessfull");
    }

    /**
     * remove ip-telefony {@link IPTelefony}
     *
     * @param user
     *            by user id {@link User}
     *
     */

    private void removeIPtelefony(User user) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM ip_telefony_characteristic WHERE user_id=?")) {
            preparedStatement.setInt(1,user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("remove IPTelefony " + user.getLogin(), e);
        }

        LOGGER.info("removeIPTelefony " + user.getLogin() + " succsessfull");
    }

    /**
     * remove television {@link Television}
     *
     * @param user
     *            by user id {@link User}
     *
     */

    private void removeTelevision(User user) {
        try(Connection connection = getConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM television_characteristic WHERE user_id=?")) {
            preparedStatement.setInt(1, user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("remove Television " + user.getLogin(), e);
        }

        LOGGER.info("removeTelevision " + user.getLogin() + " succsessfull");
    }


}
