package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.PaymentDAO;
import finalproject.entity.Payment;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPaymentsAdminCommand is controller command which admin can see paid
 * or unpaid payments
 * its only abonent command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */

public class ViewPaymentsAdminCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "payments";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        String status = wrapper.getParameter("status");
        boolean paidStatus = Boolean.valueOf(status);

        List<Payment> payments =  (List<Payment>) wrapper.getSession().getAttribute("all payments");

        if (payments == null) {
            DAOFactory factory = DAOFactory.getInstance();
            factory.setTest(isTest);
            PaymentDAO paymentDAO = factory.getPaymentDAO();

            if (status == null || status.equals("all")) {
                payments = paymentDAO.getAllPayments();
            } else {
                payments = paymentDAO.getPaymentsFilter(paidStatus);
            }

            wrapper.getSession().setAttribute("all payments", payments);

        } else {

            if (status != null && !status.equals("all")) {
                List<Payment> choseList = new ArrayList<>();
                for (Payment payment : payments) {
                    if (payment.isPaid() == paidStatus) {
                        choseList.add(payment);
                    }
                }
                payments = choseList;
            }

        }
        wrapper.setAttribute("payments", payments);
        return "admin-page/payments.jsp";
    }

}
