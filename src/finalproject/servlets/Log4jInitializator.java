package finalproject.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Servlet that started first and initialize Log4j
 *
 * @author Andrew
 * @version 1.0
 *
 */
public class Log4jInitializator extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Log4jInitializator.class);

    /**
     * initialize log4j
     */

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-init-file");
        System.out.println("Prefix log4j" + prefix);

        if (file != null) {
            PropertyConfigurator.configure(prefix+file);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("log4j logging started:" + prefix + file);
            }

        } else {
            LOGGER.error("Log4j not configurated");
        }

    }

}
