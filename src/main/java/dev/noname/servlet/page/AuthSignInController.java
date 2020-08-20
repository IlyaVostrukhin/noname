package dev.noname.servlet.page;

import dev.noname.Constants;
import dev.noname.model.CurrentAccount;
import dev.noname.servlet.AbstractController;
import dev.noname.util.RoutingUtils;
import dev.noname.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/sign-in")
@ServletSecurity
public class AuthSignInController extends AbstractController {
    private static final long serialVersionUID = -2817117553267457969L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtils.isCurrentAccountCreated(req)) {
            RoutingUtils.redirect("/orders", req, resp);
        } else {
            String targetUrl = req.getParameter("target");
            if (targetUrl != null) {
                req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN, targetUrl);
                req.getSession().setAttribute("target", targetUrl);
            }
            RoutingUtils.forwardToPage("sign-in.jsp", req, resp);
            req.getSession().setAttribute("target", null);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        if (SessionUtils.isCurrentAccountCreated(req)) {
            RoutingUtils.redirect("/orders", req, resp);
        } else if (getAuthService().isExist(login, password)) {
            CurrentAccount currentAccount = getAuthService().getAccount(login, password);
            SessionUtils.setCurrentAccount(req, currentAccount);

            String targetUrl = (String) req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
            if (targetUrl != null) {
                req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN, targetUrl);
                RoutingUtils.redirect(URLDecoder.decode(targetUrl, "UTF-8"), req, resp);
            } else {
                RoutingUtils.redirect("/orders", req, resp);
            }
        } else {
            if (req.getSession().getAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN) != null) {
                req.getSession().removeAttribute(Constants.SUCCESS_REDIRECT_URL_AFTER_SIGNIN);
            }
            req.getSession().setAttribute("error", true);
            RoutingUtils.redirect("/sign-in", req, resp);
        }
    }
}
