package dev.noname.exception;

import javax.servlet.http.HttpServletResponse;

public class ResourceNotFoundException extends AbstractApplicationException {
    private static final long serialVersionUID = 5642608470279887642L;

    public ResourceNotFoundException(String s) {
        super(s, HttpServletResponse.SC_NOT_FOUND);
    }
}
