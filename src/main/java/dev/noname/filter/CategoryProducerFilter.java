package dev.noname.filter;

import dev.noname.service.ProductService;
import dev.noname.service.impl.ServiceManager;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CategoryProducerFilter")
public class CategoryProducerFilter extends AbstractFilter {

    private ProductService productService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        productService = ServiceManager.getInstance(filterConfig.getServletContext()).getProductService();
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("category_list", productService.listAllCategories());
        request.setAttribute("producer_list", productService.listAllProducer());
        chain.doFilter(request, response);
    }
}
