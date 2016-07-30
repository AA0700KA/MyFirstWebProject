package finalproject.command;

import finalproject.wrappers.IRequestWrapper;

/**
 * ExitCommand is controller command which user logout from his page
 * its overall command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */

public class ExitCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "exit";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        wrapper.getSession().removeAttribute("user");
        wrapper.getSession().removeAttribute("services");
        wrapper.getSession().removeAttribute("admin");
        Command main = new MainCommand();
        return main.execute(wrapper, isTest);
    }

}
