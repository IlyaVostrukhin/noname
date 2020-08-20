package dev.noname.servlet.page;

import dev.noname.servlet.AbstractController;
import dev.noname.util.RoutingUtils;
import dev.noname.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orders")
public class MyOrdersController extends AbstractController {
    private static final long serialVersionUID = 5248497245645333329L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToPage("orders.jsp", req, resp);
    }
}
