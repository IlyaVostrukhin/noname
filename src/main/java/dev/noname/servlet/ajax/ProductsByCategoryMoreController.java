package dev.noname.servlet.ajax;

import dev.noname.servlet.page.AbstractController;
import dev.noname.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/html/more/products/*")
public class ProductsByCategoryMoreController extends AbstractController {
    private static final long serialVersionUID = -453769807367748746L;
    private static final int SUBSTRING_INDEX = "/ajax/html/more/products".length();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String categoryUrl = req.getRequestURI().substring(SUBSTRING_INDEX);
        RoutingUtils.forwardToFragment("product-list.jsp", req, resp);
    }
}
