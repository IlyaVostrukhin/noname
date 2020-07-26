package dev.noname.filter;

import ch.qos.logback.classic.Logger;
import dev.noname.util.UrlUtils;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {
    protected final Logger LOGGER = (Logger) LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getRequestURI();
        if (UrlUtils.isMediaUrl(url) || UrlUtils.isStaticUrl(url)) {
            chain.doFilter(request, response);
        } else {
            doFilter(req, resp, chain);
        }
    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException;


    @Override
    public void destroy() {
    }
}
