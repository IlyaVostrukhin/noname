package dev.noname.listener;

import ch.qos.logback.classic.Logger;
import dev.noname.service.impl.ServiceManager;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ApplicationListener.class);
    private ServiceManager serviceManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            serviceManager = ServiceManager.getInstance(sce.getServletContext());
        } catch (RuntimeException e) {
            LOGGER.error("Application 'NoName' init failed: " + e.getMessage(), e);
            throw e;
        }
        LOGGER.info("Application 'NoName' initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceManager.close();
        LOGGER.info("Application 'NoName' destroyed");
    }
}
