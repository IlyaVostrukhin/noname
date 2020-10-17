package dev.noname.servlet.ajax;

import dev.noname.Constants;
import dev.noname.entity.Order;
import dev.noname.servlet.AbstractController;
import dev.noname.util.RoutingUtils;
import dev.noname.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ajax/html/more/my-orders")
public class MyOrdersMoreController extends AbstractController {
    private static final long serialVersionUID = -1300923158449377312L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = getOrderService().listMyOrders(SessionUtils.getCurrentAccount(req), getPage(req), Constants.ORDERS_PER_PAGE);
        req.setAttribute("orders", orders);
        RoutingUtils.forwardToFragment("orders-tbody.jsp", req, resp);
    }
}
