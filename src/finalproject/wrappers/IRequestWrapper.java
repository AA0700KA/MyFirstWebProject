package finalproject.wrappers;

import javax.servlet.http.HttpSession;

/**
 * Interface wropper on request that can use in programm or in JUnit
 *
 * @author Andrew
 * @version 1.0
 *
 */
public interface IRequestWrapper {

    /**
     * Request value of parameter key
     */

    String getParameter(String key);

    /**
     * get session user
     */

    HttpSession getSession();

    /**
     * set attribute request
     */

    void setAttribute(String key, Object value);

}
