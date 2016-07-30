package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.PaymentDAO;
import finalproject.dao.ServiceDAO;
import finalproject.entity.services.Service;
import finalproject.entity.users.User;

import java.util.Map;

/**
 * RemoveServiceCommand is controller command whisch abonent can remove service
 * from his services
 * its only abonent command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */

public class RemoveServiceCommand implements Command {

    /**
     * Describe type of command in CommandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "remove_service";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        int id = Integer.parseInt(wrapper.getParameter("id"));
        User user = (User) wrapper.getSession().getAttribute("user");
        Map<Integer, Service> serviceMap = (Map<Integer, Service>) wrapper.getSession().getAttribute("services");
        Service service = serviceMap.get(id);
        service.setId(id);
        serviceMap.remove(id);
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(isTest);
        PaymentDAO paymentDAO = factory.getPaymentDAO();
        ServiceDAO serviceDAO = factory.getServiceDAO();
        serviceDAO.remove(service, user);
        paymentDAO.removeService(service, user);
        return "/forward?action=my_payments";
    }

}
