package dev.noname.servlet.ajax;

import dev.noname.Constants;
import dev.noname.entity.Product;
import dev.noname.form.SearchForm;
import dev.noname.servlet.AbstractController;
import dev.noname.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ajax/html/more/search")
public class SearchResultMoreController extends AbstractController {
    private static final long serialVersionUID = 5374626608464094999L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        SearchForm searchForm = createSearchForm(req);
        List<Product> products = getProductService()
                .listProductsBySearchForm(searchForm, getPage(req), Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        req.setAttribute("products", products);
        RoutingUtils.forwardToFragment("product-list.jsp", req, resp);
    }
}
