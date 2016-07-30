package finalproject.command;

import finalproject.wrappers.IRequestWrapper;

/**
 * Interface of basics commands method
 *
 * @author Andrew
 * @version 1.1  26/07/2016
 */

public interface Command {

    /**
     * Method prepares web-page for controller.
     *
     * @param wrapper
     *            the wrapper of HttpServletRequest object that contains the client's
     *            request
     * @param isTest
     *            A flag that indicates the mode in which the command is executed:
     *            JUnit test or Apache Tomcat Server
     * @return local directory of web-page
     */

    String execute(IRequestWrapper wrapper, boolean isTest);

}
