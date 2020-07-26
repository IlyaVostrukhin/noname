package dev.noname.service;

import javax.servlet.ServletContext;

public class ServiceManager {

    private BusinessService businessService;

    private ServiceManager(ServletContext context) {
    }

    public BusinessService getBusinessService() {
        return businessService;
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
}
