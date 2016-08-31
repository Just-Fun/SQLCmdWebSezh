package ua.com.juja.sqlcmd.controller.web;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.service.Service;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleksandr.baglai on 09.12.2015.
 */
public abstract class AbstractAction implements Action {

    protected Service service;

    public AbstractAction(Service service) {
        this.service = service;
    }

    protected void goToJsp(ServletRequest req, ServletResponse resp, String path) throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, resp);
    }

    protected DatabaseManager getManager(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DatabaseManager manager = (DatabaseManager) req.getSession().getAttribute("manager");

        if (manager != null) {
            return manager;
        } else {
            resp.sendRedirect(resp.encodeRedirectURL("connect"));
            return DatabaseManager.NULL;
        }
    }

    protected void forwardToSuccess(ServletRequest req, ServletResponse resp, String msg) throws ServletException, IOException {
        req.setAttribute("message", msg);
        req.getRequestDispatcher("success.jsp").forward(req, resp);
    }

    protected void forwardToError(ServletRequest request, ServletResponse response, Exception e) {
        request.setAttribute("message", e.getMessage());
        try {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (ServletException | IOException e1) {
            e.printStackTrace();
        }
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp) {
        // do nothing
    }

    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // do nothing
    }


}
