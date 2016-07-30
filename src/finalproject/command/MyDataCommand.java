package finalproject.command;

import finalproject.wrappers.IRequestWrapper;

/**
 * MyDataCommand is controller command which abonent show his data
 * its only abonent command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */
public class MyDataCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "my_data";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        return "abonent-page/user_data.jsp";
    }

}
