package dev.noname.servlet;

import dev.noname.service.BusinessService;
import dev.noname.service.OrderService;
import dev.noname.service.ProductService;
import dev.noname.service.impl.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet {
    private static final long serialVersionUID = 5205240208995791465L;

    private ProductService productService;
    private OrderService orderService;

    @Override
    public final void init() throws ServletException {
        productService = ServiceManager.getInstance(getServletContext()).getProductService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
    }

    public final ProductService getProductService() {
        return productService;
    }

    public final OrderService getOrderService() { return orderService; }
}
