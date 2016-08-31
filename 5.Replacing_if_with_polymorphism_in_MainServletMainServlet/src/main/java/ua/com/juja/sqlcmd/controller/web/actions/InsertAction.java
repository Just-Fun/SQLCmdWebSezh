package ua.com.juja.sqlcmd.controller.web.actions;

import ua.com.juja.sqlcmd.controller.web.AbstractAction;
import ua.com.juja.sqlcmd.model.PGException;
import ua.com.juja.sqlcmd.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by oleksandr.baglai on 09.12.2015.
 */
public class InsertAction extends AbstractAction {

    public InsertAction(Service service) {
        super(service);
    }

    @Override
    public boolean canProcess(String url) {
        return url.startsWith("/insert");
    }

    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tableName = req.getParameter("tableName");
        Set<String> columnName = service.tableHead(getManager(req, resp), tableName);
        req.setAttribute("columnName", columnName);
        goToJsp(req, resp, "insert.jsp");
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp) {
        String[] paramName = req.getParameterValues("name[]");
        String[] paramValues = req.getParameterValues("value[]");
        String tableName = req.getParameter("tableName");
        try {
            service.create(getManager(req, resp), tableName, paramName, paramValues);
            forwardToSuccess(req, resp, "вставка в таблицу " + tableName + " прошла успешно!");
        } catch (PGException | ServletException | IOException e) {
            this.forwardToError(req, resp, e);
        }
    }

}
