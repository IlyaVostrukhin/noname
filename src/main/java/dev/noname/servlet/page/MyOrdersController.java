package dev.noname.servlet.page;

import dev.noname.Constants;
import dev.noname.entity.Order;
import dev.noname.model.CurrentAccount;
import dev.noname.servlet.AbstractController;
import dev.noname.util.RoutingUtils;
import dev.noname.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class MyOrdersController extends AbstractController {
    private static final long serialVersionUID = 5248497245645333329L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
        List<Order> orders = getOrderService().listMyOrders(currentAccount, 1, Constants.ORDERS_PER_PAGE);
        req.setAttribute("orders", orders);
        int orderCount = getOrderService().countMyOrders(currentAccount);
        req.setAttribute("pageCount", getPageCount(orderCount, Constants.ORDERS_PER_PAGE));
        RoutingUtils.forwardToPage("orders.jsp", req, resp);
    }
}
