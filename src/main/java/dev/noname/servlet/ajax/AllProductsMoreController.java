package dev.noname.servlet.ajax;

import dev.noname.servlet.page.AbstractController;
import dev.noname.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/html/more/products")
public class AllProductsMoreController extends AbstractController {
    private static final long serialVersionUID = 2043372676580869142L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        RoutingUtils.forwardToFragment("product-list.jsp", req, resp);
    }
}
