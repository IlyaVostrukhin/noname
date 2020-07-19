package dev.noname.servlet;

import dev.noname.model.ShoppingCart;
import dev.noname.model.ShoppingCartItem;
import dev.noname.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/current-cart")
public class ShoppingCartServlet extends HttpServlet {

    private static final long serialVersionUID = -2211472973968974582L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");
        if ("clear".equals(cmd)) {
            SessionUtils.clearCurrentShoppingCart(req, resp);
        } else if ("invalidate".equals(cmd)) {
            req.getSession().invalidate();
        } else if ("add".equals(cmd)) {
            addProduct(req, resp);
        } else {
            sync(req, resp);
        }

        showShoppingCart(req, resp);
    }

    protected void showShoppingCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/shopping-cart.jsp").forward(req, resp);
    }

    protected void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        Random r = new Random();
        shoppingCart.addProduct(r.nextInt(2), r.nextInt(1) + 1);
    }

    protected void sync(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SessionUtils.isCurrentShoppingCartCreated(req)) {
            Cookie cookie = SessionUtils.findShoppingCartCookie(req);
            if (cookie != null) {
                ShoppingCart shoppingCart = shoppingCartFromString(cookie.getValue());
                SessionUtils.setCurrentShoppingCart(req, shoppingCart);
            }
        } else {
            ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
            String cookieValue = shoppingCartToString(shoppingCart);
            SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
        }
    }

    protected String shoppingCartToString(ShoppingCart shoppingCart) {
        StringBuilder result = new StringBuilder();
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
            result.append(shoppingCartItem.getIdProduct()).append("-").
                    append(shoppingCartItem.getCount()).append("|");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    protected ShoppingCart shoppingCartFromString(String cookieValue) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = cookieValue.split("\\|");
        for (String item : items) {
            String[] data = item.split("-");
            try {
                int idProduct = Integer.parseInt(data[0]);
                int count = Integer.parseInt(data[1]);
                shoppingCart.addProduct(idProduct, count);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return shoppingCart;
    }
}
