package ua.com.juja.sqlcmd.controller.web.actions;

import ua.com.juja.sqlcmd.controller.web.AbstractAction;
import ua.com.juja.sqlcmd.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleksandr.baglai on 09.12.2015.
 */
public class ErrorAction extends AbstractAction {

    public ErrorAction(Service service) {
        super(service);
    }

    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "Ошибка");
        goToJsp(req, resp, "error.jsp");
    }

    @Override
    public boolean canProcess(String url) {
        return true;
    }
}
