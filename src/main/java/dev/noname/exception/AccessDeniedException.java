package dev.noname.exception;

import javax.servlet.http.HttpServletResponse;

public class AccessDeniedException extends AbstractApplicationException {
    private static final long serialVersionUID = -4699938276153091027L;

    public AccessDeniedException(String s) {
        super(s, HttpServletResponse.SC_FORBIDDEN);
    }
}
