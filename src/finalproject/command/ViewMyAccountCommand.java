package finalproject.command;

import finalproject.wrappers.IRequestWrapper;
import finalproject.entity.users.Administrator;
import finalproject.entity.users.User;

import javax.servlet.http.HttpSession;

/**
 * ViewMyAccountCommand is controller command which abonent or admin go to
 * his account data otherwise user go login page
 * its overall command
 *
 * @author Andrew
 * @version 1.0  23/07/2016
 */

public class ViewMyAccountCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "myaccount";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        HttpSession session = wrapper.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return"/auth.jsp";
        } else {
            if (user instanceof Administrator) {
                return "admin-page/registration.jsp";
            } else {
                Command command = new AbonentPaymentsCommand();
                return command.execute(wrapper, isTest);
            }
        }

    }

}
