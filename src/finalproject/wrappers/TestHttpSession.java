package finalproject.wrappers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Test HttpSession impliments {@link HttpSession}
 *
 * @author Andrew
 * @version 1.0
 *
 */

public class TestHttpSession implements HttpSession {

    /**
     * session atributes map
     */

    private Map<String, Object> attributes;

    public TestHttpSession() {
        attributes = new HashMap<>();
    }

    @Override
    public void setAttribute(String s, Object o) {
       attributes.put(s,o);
    }

    @Override
    public void removeAttribute(String s) {
       attributes.remove(s);
    }

    @Override
    public Object getAttribute(String s) {
        return attributes.get(s);
    }

    /**
     * clear session attributes
     */

    @Override
    public void invalidate() {
        attributes.clear();
    }

    @Override
    public void putValue(String s, Object o) {

    }

    @Override
    public void setMaxInactiveInterval(int i) {

    }

    @Override
    public void removeValue(String s) {

    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getValue(String s) {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }
}
