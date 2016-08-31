package ua.com.juja.sqlcmd.controller.web.actions;

import ua.com.juja.sqlcmd.controller.web.AbstractAction;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleksandr.baglai on 09.12.2015.
 */
public class FindAction extends AbstractAction {

    public FindAction(Service service) {
        super(service);
    }

    @Override
    public boolean canProcess(String url) {
        return url.startsWith("/find");
    }

    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseManager manager = getManager(req, resp);
        String tableName = req.getParameter("tableName");
        req.setAttribute("items", service.tableData(manager, tableName));
        req.setAttribute("itemsHead", service.tableHead(manager, tableName));
        goToJsp(req, resp, "find.jsp");
    }

}
