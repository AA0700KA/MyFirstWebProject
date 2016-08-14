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
                && !path.equals("/images/Internet-Header.jpg") && !path.equals("/images/header_internet.png") && !path.equals("/favicon.ico")
                && !path.equals("/images/perth-best-internet-HEADER.jpg") && !path.equals("/main.jsp")
                && !path.equals("/connect_page.jsp") && !path.equals("/styles/main_styles.css")
                && !path.equals("/styles/payments_and_table.css") && !path.equals("/styles/register_styles.css")
                && !path.equals("/styles/fill_up_balance.css") && !path.equals("/styles/auth_styles.css")
                && !path.equals("/images/air_gradient.jpg") && !path.equals("/images/Internet-marketing1.jpg")
                && !path.equals("/images/telefony.png") && !path.equals("/images/television.jpg")
                && !path.equals("/images/ip-telefony.png")) {
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
