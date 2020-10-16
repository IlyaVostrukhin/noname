package dev.noname.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class WebUtils {

    public static Cookie findCookie(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    if (cookie.getValue() != null && !"".equals(cookie.getValue())) {
                        return cookie;
                    }
                }
            }
        }
        return null;
    }

    public static void setCookie(String name, String value, int age, HttpServletResponse resp) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
    }

    public static String getCurrentRequestUrl(HttpServletRequest req) {
        String query = req.getQueryString();
        if (query == null) {
            return req.getRequestURI();
        } else {
            return req.getRequestURI() + "?" + query;
        }
    }

    private WebUtils() {
    }
}
