package dev.noname.util;

import dev.noname.Constants;
import dev.noname.model.CurrentAccount;
import dev.noname.model.ShoppingCart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionUtils {

    public static ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {
        ShoppingCart shoppingCart =
                (ShoppingCart) req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            setCurrentShoppingCart(req, shoppingCart);
        }
        return shoppingCart;
    }

    public static boolean isCurrentShoppingCartCreated(HttpServletRequest req) {
        return req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART) != null;
    }

    public static void setCurrentShoppingCart(HttpServletRequest req, ShoppingCart shoppingCart) {
        req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
    }

    public static void clearCurrentShoppingCart(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute(Constants.CURRENT_SHOPPING_CART);
        WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), null, 0, resp);
    }

    public static Cookie findShoppingCartCookie(HttpServletRequest req) {
        return WebUtils.findCookie(req, Constants.Cookie.SHOPPING_CART.getName());
    }

    public static void updateCurrentShoppingCartCookie(String cookieValue, HttpServletResponse resp) {
        WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(), cookieValue,
                Constants.Cookie.SHOPPING_CART.getTtl(), resp);
    }

    public static CurrentAccount getCurrentAccount(HttpServletRequest request) {
        return (CurrentAccount) request.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
    }

    public static void setCurrentAccount(HttpServletRequest request, CurrentAccount currentAccount) {
        request.getSession().setAttribute(Constants.CURRENT_ACCOUNT, currentAccount);
    }

    public static boolean isCurrentAccountCreated(HttpServletRequest request) {
        return getCurrentAccount(request) != null;
    }

    private SessionUtils() {
    }

}
