package finalproject.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Bad request url filter
 *
 * @author Andrew
 * @version 1.0
 *
 */
public class BadRequestFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(BadRequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest)servletRequest).getRequestURI();
        Map<String, String[]> parametersMap = servletRequest.getParameterMap();

        LOGGER.info(path);

        if (path.equals("/forward") && parametersMap.size() == 0) {
            ((HttpServletResponse)servletResponse).sendRedirect("/forward?action=main");
        } else if (path.equals("/forwardUsers") && parametersMap.size() == 0) {
            ((HttpServletResponse)servletResponse).sendRedirect("/forwardUsers?action=users");
        } else if (path.equals("/forwardPayments") && parametersMap.size() == 0) {
            ((HttpServletResponse)servletResponse).sendRedirect("/forwardPayments?action=payments");
        } else if (path.equals("/forwardMyPayments") && parametersMap.size() == 0) {
            ((HttpServletResponse) servletResponse).sendRedirect("/forwardMyPayments?action=my_payments");
        }
         else if (!path.equals("/forward") && !path.equals("/forwardUsers")
                && !path.equals("/forwardPayments") && !path.equals("/forwardMyPayments")
                && !path.equals("/abonent-page/fill_up_balance.jsp") && !path.equals("/admin-page/registration.jsp")
                && !path.equals("/Internet-Header.jpg") && !path.equals("/header_internet.png") && !path.equals("/favicon.ico")
                && !path.equals("/perth-best-internet-HEADER.jpg") && !path.equals("/main.jsp")) {
            ((HttpServletResponse)servletResponse).sendRedirect("/forward?action=error");
        }
          else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }

}
