package ua.com.juja.sqlcmd.controller.web.actions;

import ua.com.juja.sqlcmd.controller.web.AbstractAction;
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
public class ConnectAction extends AbstractAction {

    public ConnectAction(Service service) {
        super(service);
    }

    @Override
    public boolean canProcess(String url) {
        return url.equalsIgnoreCase("/connect");
    }

    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        goToJsp(req, resp, "connect.jsp");
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp) {
        String databaseName = req.getParameter("dbname");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        try {

            DatabaseManager manager = service.connect(databaseName, userName, password);
            req.getSession().setAttribute("manager", manager);

            resp.sendRedirect(resp.encodeRedirectURL("menu"));
        } catch (Exception e) {
            forwardToError(req, resp, e);
        }
    }
}
