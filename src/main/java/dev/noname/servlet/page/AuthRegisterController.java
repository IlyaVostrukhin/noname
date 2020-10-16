package dev.noname.servlet.page;

import dev.noname.Constants;
import dev.noname.entity.Account;
import dev.noname.servlet.AbstractController;
import dev.noname.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

@WebServlet("/registration")
public class AuthRegisterController extends AbstractController {
    private static final long serialVersionUID = 5743616753930555902L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String targetUrl = req.getParameter("target");
        if (targetUrl != null) {
            req.getSession().setAttribute("target", targetUrl);
        }
        RoutingUtils.forwardToPage("registration.jsp", req, resp);
        req.getSession().setAttribute("target", null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String email = req.getParameter("inputEmail");
        String password = req.getParameter("inputPassword");
        String surName = req.getParameter("surName");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String city = req.getParameter("inputCity");
        Integer postCode = Integer.parseInt(req.getParameter("postcode"));
        String address = req.getParameter("inputAddress");
        //TODO: Добавить проверку по уникальным полям
        Account account = getAuthService().register(login, email, password, surName,
                firstName, lastName, phone, city, postCode, address);
        if (account != null) {
            req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, account);
            String targetUrl = (String) req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
            if (targetUrl != null) {
                req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN, targetUrl);
                RoutingUtils.redirect(URLDecoder.decode(targetUrl, "UTF-8"), req, resp);
            } else {
                RoutingUtils.redirect("/orders", req, resp);
            }
        } else {
            LOGGER.error("User cannot be created. Login - {}, Email - {}, Phone - {}", login, email, phone);
        }
    }
}
