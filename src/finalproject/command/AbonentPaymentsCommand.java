package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.PaymentDAO;
import finalproject.entity.Payment;
import finalproject.entity.users.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * AbonentPaymentsCommand is controller command which show your payments
 * its only abonent command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */

public class AbonentPaymentsCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "my_payments";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        HttpSession session = wrapper.getSession();
        User user = (User) session.getAttribute("user");
        List<Payment> myPayments = (List<Payment>) session.getAttribute("my payments");

        if (myPayments == null) {
            DAOFactory factory = DAOFactory.getInstance();
            factory.setTest(isTest);
            PaymentDAO paymentDAO = factory.getPaymentDAO();
            myPayments = paymentDAO.getAbonentPayments(user);
            session.setAttribute("my payments", myPayments);
        }

        wrapper.setAttribute("payments", myPayments);
        return "abonent-page/user_payments.jsp";
    }

}
