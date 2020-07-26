package dev.noname.servlet.page;

import dev.noname.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products")
public class AllProductsController extends AbstractController {
    private static final long serialVersionUID = -436075988649095158L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        List<?> products = getProductService().getProducts();
//        req.setAttribute("products", products);
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        RoutingUtils.forwardToPage("products.jsp", req, resp);
    }
}
