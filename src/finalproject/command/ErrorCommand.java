package finalproject.command;

import finalproject.wrappers.IRequestWrapper;

/**
 * ErrorCommand is controller command when user write uncorrect uri page
 *
 * @author Andrew
 * @version 1.0  23/07/2016
 */
public class ErrorCommand implements Command {

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        return "/error.jsp";
    }

}
