package dev.noname.servlet.page;

import dev.noname.servlet.AbstractController;
import dev.noname.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ErrorController extends AbstractController {
    private static final long serialVersionUID = -2958590846781392662L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToPage("error.jsp", req, resp);
    }
}
