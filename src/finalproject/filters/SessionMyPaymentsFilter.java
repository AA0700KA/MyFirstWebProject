package finalproject.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter remove abonent payments from session
 *
 * @author Andrew
 * @version 1.0
 *
 */
public class SessionMyPaymentsFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(SessionMyPaymentsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest) servletRequest).getRequestURI();

        if (!path.equals("/forwardMyPayments") && !path.equals("/Internet-Header.jpg") && !path.equals("/header_internet.png") && !path.equals("/favicon.ico")
                && !path.equals("/perth-best-internet-HEADER.jpg")
                && ((HttpServletRequest)servletRequest).getSession().getAttribute("my payments") != null) {
            ((HttpServletRequest)servletRequest).getSession().removeAttribute("my payments");
            LOGGER.info("Remove my payments");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
