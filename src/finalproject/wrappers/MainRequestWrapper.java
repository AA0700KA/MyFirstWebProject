package finalproject.wrappers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Request wropper to programm ipliments {@link IRequestWrapper}
 *
 * @author Andrew
 * @version 1.0
 */

public class MainRequestWrapper implements IRequestWrapper {

    private HttpServletRequest request;

    public MainRequestWrapper(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getParameter(String key) {
        return request.getParameter(key);
    }

    @Override
    public HttpSession getSession() {
        return request.getSession();
    }

    @Override
    public void setAttribute(String key, Object value) {
        request.setAttribute(key, value);
    }

}
