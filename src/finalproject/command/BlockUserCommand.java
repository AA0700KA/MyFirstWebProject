package finalproject.command;


import finalproject.wrappers.IRequestWrapper;
import finalproject.dao.DAOFactory;
import finalproject.dao.UserDAO;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.User;

import java.util.List;

/**
 * BlockUserCommand is controller command which admin can block another users
 * its only admin command
 *
 * @author Andrew
 * @version 1.0  23/07/2016
 */

public class BlockUserCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "block_user";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        int id = Integer.parseInt(wrapper.getParameter("id"));
        String submit = wrapper.getParameter("submit");
        DAOFactory factory = DAOFactory.getInstance();
        factory.setTest(isTest);
        UserDAO userDAO = factory.getUserDAO();

        if (submit.equals("Block user") || submit.equals("Блокировать")) {
            userDAO.blockUserById(id,1);
        } else {
            userDAO.blockUserById(id,0);
        }

        List<User> users = (List<User>) wrapper.getSession().getAttribute("all users");

        if (users != null) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == id) {
                    Abonent abonent = (Abonent)users.get(i);
                    abonent.setBlocked(!abonent.isBlocked());
                }
            }
        }

        Command viewUsers = new ViewAllUsersAdminCommand();
        return viewUsers.execute(wrapper, isTest);
    }

}
