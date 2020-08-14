package dev.noname.service.impl;

import ch.qos.logback.classic.Logger;
import dev.noname.service.AuthService;
import dev.noname.service.OrderService;
import dev.noname.service.ProductService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ServiceManager {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceManager.class);

    private final Properties applicationProperties = new Properties();
    private final BasicDataSource dataSource;
    private final ProductService productService;
    private final OrderService orderService;
    private final AuthService authService;

    private ServiceManager(ServletContext context) {
        loadApplicationProperties();
        dataSource = createDataSource();
        productService = new ProductServiceImpl(dataSource);
        orderService = new OrderServiceImpl(dataSource);
        authService = new AuthServiceImpl(dataSource);
    }

    private BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDefaultAutoCommit(false);
        dataSource.setRollbackOnReturn(true);
        dataSource.setDriverClassName(getApplicationProperty("db.driver"));
        dataSource.setUrl(getApplicationProperty("db.url"));
        dataSource.setUsername(getApplicationProperty("db.username"));
        dataSource.setPassword(getApplicationProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
        dataSource.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));
        return dataSource;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() { return orderService; }

    public AuthService getAuthService() { return authService; }

    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    public void close() {
        try {
            dataSource.close();
        } catch (SQLException exc) {
            LOGGER.error("Close datasource failed: " + exc.getMessage(), exc);
        }
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
