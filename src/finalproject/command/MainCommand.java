package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.ServiceDAO;
import finalproject.entity.services.Service;

import java.util.List;

/**
 * MainCommand is controller main page, load all services which user and admin can watch
 * and abonent can add or remove from his services
 * its only abonent command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */

public class MainCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "main";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(isTest);
        ServiceDAO serviceDAO = factory.getServiceDAO();
        List<Service> services = serviceDAO.getServices();
        wrapper.setAttribute("services", services);
        System.out.println(services);
        return "/main.jsp";
    }

}
