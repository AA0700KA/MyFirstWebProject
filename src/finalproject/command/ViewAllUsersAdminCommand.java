package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.UserDAO;
import finalproject.entity.users.User;

import java.util.List;

/**
 * ViewAllUsersAdminCommand is controller command which show all users to admin
 * its only admin command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */

public class ViewAllUsersAdminCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "users";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        List<User> users = (List<User>) wrapper.getSession().getAttribute("all users");

        if (users == null) {
            DAOFactory factory = DAOFactory.getInstance();
            factory.setTest(isTest);
            UserDAO userDAO = factory.getUserDAO();
            users = userDAO.getNoAdminUsers();
            wrapper.getSession().setAttribute("all users", users);
        }

        wrapper.setAttribute("users", users);
        return "admin-page/allusers.jsp";
    }

}
