package dev.noname.servlet.page;

import dev.noname.servlet.AbstractController;
import dev.noname.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/sign-in")
public class AuthController extends AbstractController {
    private static final long serialVersionUID = -2817117553267457969L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToPage("/sign-in.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            RoutingUtils.forwardToPage("/products.jsp", req, resp);
        } else if (getAuthService().isExist(login, password)) {
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("password", password);

            RoutingUtils.forwardToPage("/products.jsp", req, resp);
        } else {
            RoutingUtils.redirect("/sign-in.jsp", req, resp);
        }
    }
}
