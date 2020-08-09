package dev.noname.servlet.ajax;


import dev.noname.form.ProductForm;
import dev.noname.model.ShoppingCart;
import dev.noname.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/json/product/add")
public class AddProductController extends AbstractProductController {

    private static final long serialVersionUID = 2283839742715736430L;


    @Override
    protected void processProductForm(ProductForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        getOrderService().addProductToShoppingCart(form, shoppingCart);
        String cookieValue = getOrderService().serializeShoppingCart(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
    }
}
