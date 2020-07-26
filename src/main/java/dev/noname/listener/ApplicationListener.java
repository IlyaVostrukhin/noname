package dev.noname.listener;

import dev.noname.service.ServiceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {

    private ServiceManager serviceManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        serviceManager = ServiceManager.getInstance(sce.getServletContext());
        serviceManager.getBusinessService().doSomething();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceManager.getBusinessService().doSomething();
        serviceManager.close();
    }
}
