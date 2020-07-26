package dev.noname.service.impl;

import dev.noname.service.BusinessService;
import dev.noname.service.OrderService;
import dev.noname.service.ProductService;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServiceManager {

    private final Properties applicationProperties = new Properties();
    private final ProductService productService;
    private final OrderService orderService;

    private ServiceManager(ServletContext context) {
        loadApplicationProperties();
        productService = new ProductServiceImpl();
        orderService = new OrderServiceImpl();
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    public void close() {
    }

    public String getApplicationProperty(String key) {
        return applicationProperties.getProperty(key);
    }

    private void loadApplicationProperties() {
        try (InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            applicationProperties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
