package dev.noname.servlet;

import dev.noname.service.BusinessService;
import dev.noname.service.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet {
    private static final long serialVersionUID = 5205240208995791465L;

    private BusinessService businessService;

    @Override
    public final void init() throws ServletException {
        businessService = ServiceManager.getInstance(getServletContext()).getBusinessService();
    }

    public final BusinessService getBusinessService() {
        return businessService;
    }
}
