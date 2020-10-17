package dev.noname.filter;

import dev.noname.Constants;
import dev.noname.util.RoutingUtils;
import dev.noname.util.SessionUtils;
import dev.noname.util.UrlUtils;
import dev.noname.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CheckAuthentificationFilter")
public class CheckAuthentificationFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (SessionUtils.isCurrentAccountCreated(request)) {
            chain.doFilter(request, response);
        } else {
            String requestUrl = WebUtils.getCurrentRequestUrl(request);
            if (UrlUtils.isAjaxUrl(requestUrl)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("401");
            } else {
                request.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN, requestUrl);
                RoutingUtils.redirect("/sign-in", request, response);
            }
        }
    }
}
