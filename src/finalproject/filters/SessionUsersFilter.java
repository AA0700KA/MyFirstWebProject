package finalproject.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter that remove all users from session
 *
 * @author Andrew
 * @version 1.0
 *
 */
public class SessionUsersFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(SessionUsersFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest)servletRequest).getRequestURI();

        if (!path.equals("/forwardUsers") && !path.equals("/Internet-Header.jpg") && !path.equals("/header_internet.png") && !path.equals("/favicon.ico")
                && !path.equals("/perth-best-internet-HEADER.jpg")
                && ((HttpServletRequest)servletRequest).getSession().getAttribute("all users") != null) {
            ((HttpServletRequest)servletRequest).getSession().removeAttribute("all users");
            LOGGER.info("Remove users");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
