package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.ServiceDAO;
import finalproject.dao.UserDAO;
import finalproject.entity.services.Service;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.Administrator;
import finalproject.entity.users.User;

import java.util.Map;

/**
 * LoginCommand is controller command which user can sing in on the site
 * its overall command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */

public class LoginCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "login";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        String login = wrapper.getParameter("login");
        String password = wrapper.getParameter("password");
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(isTest);
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getUser(login);
        ServiceDAO serviceDAO = factory.getServiceDAO();
        Map<Integer, Service> userServices = serviceDAO.getUserServices(user);
        boolean blocked = (user instanceof Abonent) ? ((Abonent) user).isBlocked() : false;

        if (user != null && password.equals(user.getPassword()) && !blocked) {
            System.out.println(user);
            wrapper.getSession().setAttribute("user", user);
            wrapper.getSession().setAttribute("services", userServices);
            boolean isAdmin;

            if (user instanceof Administrator) {
                isAdmin = true;
            } else {
                isAdmin = false;
            }

            wrapper.getSession().setAttribute("admin", isAdmin);
            Command main = new MainCommand();
            return main.execute(wrapper, isTest);
        } else if (blocked) {
            wrapper.setAttribute("blocked", true);
        } else {
            wrapper.setAttribute("incorrect", true);
        }

        return "/auth.jsp";

    }

}
