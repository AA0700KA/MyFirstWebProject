package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.PaymentDAO;
import finalproject.dao.UserDAO;
import finalproject.entity.services.Service;
import finalproject.entity.services.SimpleTelefony;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.User;

/**
 * RegisterCommand is controller command which admin can connect new user
 * its only admin command
 *
 * @author Andrew
 * @version 1.0  23/07/2016
 */

public class RegisterCommand implements Command {

    /**
     * Describe type of command in CommandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "register";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        String login = wrapper.getParameter("login");
        String password = wrapper.getParameter("password");
        String name = wrapper.getParameter("name");
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(isTest);
        UserDAO userDAO = factory.getUserDAO();
        Service firstService = new SimpleTelefony();
        User existsUser = userDAO.getUser(login);

        if (existsUser == null && login.length() > 0 && password.length() > 0 && name.length() > 0) {

            User user = new Abonent();
            user.setLogin(login);
            user.setName(name);
            user.setPassword(password);
            userDAO.addUser(user);
            PaymentDAO paymentDAO = factory.getPaymentDAO();
            User payUser = userDAO.getUser(user.getLogin());
            paymentDAO.addPayment(payUser, firstService);
            wrapper.setAttribute("registerResponse", true);
            return "admin-page/adminPage.jsp";

        } else if (login.length() == 0 || password.length() == 0 || name.length() == 0) {
            wrapper.setAttribute("incorrect", true);
        } else {
            wrapper.setAttribute("exists", true);
        }

        return "admin-page/registration.jsp";
    }

}
