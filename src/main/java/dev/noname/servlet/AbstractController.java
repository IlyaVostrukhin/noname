package dev.noname.servlet;

import ch.qos.logback.classic.Logger;
import dev.noname.form.ProductForm;
import dev.noname.form.SearchForm;
import dev.noname.service.BusinessService;
import dev.noname.service.OrderService;
import dev.noname.service.ProductService;
import dev.noname.service.impl.ServiceManager;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractController extends HttpServlet {
    private static final long serialVersionUID = 5205240208995791465L;
    protected final Logger LOGGER = (Logger) LoggerFactory.getLogger(getClass());

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

    public final OrderService getOrderService() {
        return orderService;
    }

    public final int getPageCount(int totalCount, int itemsPerPage) {
        int result = totalCount / itemsPerPage;
        if (result * itemsPerPage != totalCount) {
            result++;
        }
        return result;
    }

    public final int getPage(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public final SearchForm createSearchForm(HttpServletRequest request) {
        return new SearchForm(
                request.getParameter("query"),
                request.getParameterValues("category"),
                request.getParameterValues("producer")
        );
    }

    public final ProductForm createProductForm(HttpServletRequest request) {
        return new ProductForm(
                Integer.parseInt(request.getParameter("idProduct")),
                Integer.parseInt(request.getParameter("count"))
        );
    }
}
