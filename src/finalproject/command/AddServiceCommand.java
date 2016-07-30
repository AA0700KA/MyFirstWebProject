package finalproject.command;


import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.PaymentDAO;
import finalproject.dao.ServiceDAO;
import finalproject.entity.services.*;
import finalproject.entity.users.User;

import java.util.Map;

/**
 * AddServiceCommand is controller command which abonent can add services to his service
 * its only abonent command
 *
 * @author Andrew
 * @version 1.0  23/07/2016
 */

public class AddServiceCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "add_service";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        String name = wrapper.getParameter("name");
        int id = Integer.parseInt(wrapper.getParameter("id"));
        Service service = ServiceFactory.getService(id);
        service.setName(name);
        User user = (User) wrapper.getSession().getAttribute("user");
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(isTest);
        ServiceDAO serviceDAO = factory.getServiceDAO();

        if (service instanceof Internet) {
            int speed = Integer.parseInt(wrapper.getParameter("speed"));
            ((Internet)service).setSpeed(speed);
            serviceDAO.addInternet(service, user);
        } else if (service instanceof IPTelefony) {
            String videocall = wrapper.getParameter("videocall");
            int roumingMinutes = Integer.parseInt(wrapper.getParameter("rouming-minutes"));
            ((IPTelefony) service).setRoumingMinutes(roumingMinutes);

            if (videocall != null) {
                ((IPTelefony) service).setVideoCall(true);
            }

            serviceDAO.addIPTelefony(service, user);

        } else if (service instanceof Television) {
            int countChannels = Integer.parseInt(wrapper.getParameter("count_channels"));
            ((Television)service).setCountChanels(countChannels);
            serviceDAO.addTV(service,user);
        }


        Map<Integer, Service> serviceMap = (Map<Integer, Service>) wrapper.getSession().getAttribute("services");
        serviceMap.put(service.getId(), service);
        wrapper.getSession().setAttribute("services", serviceMap);

        PaymentDAO paymentDAO = factory.getPaymentDAO();
        paymentDAO.addPayment(user, service);
        return "/forward?action=my_payments";
    }

}
