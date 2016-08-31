package ua.com.juja.sqlcmd.controller.web.actions;

import ua.com.juja.sqlcmd.controller.web.AbstractAction;
import ua.com.juja.sqlcmd.model.DatabaseManager;
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
public class UpdateAction extends AbstractAction {

    public UpdateAction(Service service) {
        super(service);
    }

    @Override
    public boolean canProcess(String url) {
        return url.startsWith("/update");
    }

    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseManager manager = getManager(req, resp);

        String tableName = req.getParameter("tableName");
        String id = req.getParameter("id");

        Set<String> columnName = service.tableHead(manager, tableName);
        req.setAttribute("columnName", columnName);

        req.setAttribute("items", service.tableDataById(manager, tableName, Integer.parseInt(id)));

        goToJsp(req, resp, "update.jsp");
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String[] paramName = req.getParameterValues("name[]");
        String[] paramValues = req.getParameterValues("value[]");
        String tableName = req.getParameter("tableName");
        try {
            service.update(getManager(req, resp), tableName, paramName, paramValues);

            forwardToSuccess(req, resp, "Обновление в таблицу " + tableName + " прошла успешно!");
        } catch (PGException | ServletException | IOException e) {
            this.forwardToError(req, resp, e);
        }
    }

}
