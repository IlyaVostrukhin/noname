package dev.noname.filter;

import dev.noname.util.RoutingUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ErrorHandlerFilter")
public class ErrorHandlerFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            chain.doFilter(request, response);
        } catch (Throwable th) {
            String requestUrl = request.getRequestURI();
            LOGGER.error("Request " + requestUrl + " failed: " + th.getMessage(), th);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            RoutingUtils.forwardToPage("error.jsp", request, response);
        }
    }

}
