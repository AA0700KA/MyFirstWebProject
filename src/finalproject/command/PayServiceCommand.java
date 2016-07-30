package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.PaymentDAO;
import finalproject.dao.UserDAO;
import finalproject.entity.services.Service;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.User;

import java.util.Locale;
import java.util.Map;

/**
 * PayServiceCommand is controller command which abonent can pay unpaid service
 * its only abonent command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */

public class PayServiceCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "pay";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        User user = (User) wrapper.getSession().getAttribute("user");
        int price = Integer.parseInt(wrapper.getParameter("price"));
        int serviceId = Integer.parseInt(wrapper.getParameter("service_id"));
        Map<Integer, Service> services = (Map<Integer, Service>) wrapper.getSession().getAttribute("services");
        System.out.println(services);
        System.out.println("id - " + serviceId);
        Service service = services.get(serviceId);
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(isTest);
        UserDAO userDAO = factory.getUserDAO();
        int userBalance = ((Abonent)user).getBalance();

        if (userBalance >= price) {
            Locale locale = Locale.getDefault();
            ((Abonent) user).setBalance(-price);

            if (locale.getCountry().equals("EN")) {
                userDAO.updateBalance(user, -price);
            } else {
                userDAO.updateBalance(user, -price/15);
            }

            PaymentDAO paymentDAO = factory.getPaymentDAO();
            paymentDAO.payService(user, service);
            wrapper.setAttribute("pay", true);
        } else {
            wrapper.setAttribute("notPay", true);
        }

        return "abonent-page/abonentPage.jsp";
    }

}
