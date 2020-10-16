package dev.noname.servlet.page;

import dev.noname.servlet.AbstractController;
import dev.noname.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-out")
public class AuthSignOutController extends AbstractController {
    private static final long serialVersionUID = -1098297652973343449L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        RoutingUtils.redirect("/products", req, resp);
    }
}
