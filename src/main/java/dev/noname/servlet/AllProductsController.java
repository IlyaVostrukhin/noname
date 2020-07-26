package dev.noname.servlet;

import dev.noname.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class AllProductsController extends AbstractController {
    private static final long serialVersionUID = -436075988649095158L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<?> products = getBusinessService().getProducts();
        req.setAttribute("products", products);
        RoutingUtils.forwardToPage("products.jsp", req, resp);
    }
}
