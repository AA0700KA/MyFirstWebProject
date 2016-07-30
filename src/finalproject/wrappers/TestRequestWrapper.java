package finalproject.wrappers;

import finalproject.exceptions.TestParameterNotFoundException;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Request wopper to test impliments {@link IRequestWrapper}
 *
 * @author Andrew
 * @version 1.0
 *
 */

public class TestRequestWrapper implements IRequestWrapper {

    private Map<String, Object> parameters;
    private HttpSession testSession;

    public TestRequestWrapper() {
        parameters = new HashMap<>();
        testSession = new TestHttpSession();
    }

    @Override
    public HttpSession getSession() {
        return testSession;
    }

    @Override
    public String getParameter(String key) {
        Object result = parameters.get(key);

        if (result instanceof String) {
            return (String)result;
        }

        throw new TestParameterNotFoundException("Object with key " + key + " not string type");
    }

    @Override
    public void setAttribute(String key, Object value) {
        parameters.put(key, value);
    }

}
