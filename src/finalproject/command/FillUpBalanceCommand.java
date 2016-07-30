package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.UserDAO;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.User;

import java.util.Locale;

/**
 * FillUpBalanceCommand is controller command which user can fill his balance
 * its only abonent command
 *
 * @author Andrew
 * @version 1.2  29/07/2016
 */

public class FillUpBalanceCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "fill_balance";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        int balance = Integer.parseInt(wrapper.getParameter("update_balance"));
        User user = (User) wrapper.getSession().getAttribute("user");
        Locale locale = Locale.getDefault();

        ((Abonent) user).setBalance(balance);

        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(isTest);
        UserDAO userDAO = factory.getUserDAO();

        if (locale.getCountry().equals("EN")) {
            userDAO.updateBalance(user, balance);
        } else {
            userDAO.updateBalance(user, balance/15);
        }

        wrapper.setAttribute("balance", true);
        return "abonent-page/abonentPage.jsp";
    }
}
