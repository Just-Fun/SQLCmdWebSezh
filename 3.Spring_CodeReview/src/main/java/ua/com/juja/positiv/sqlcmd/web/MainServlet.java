package ua.com.juja.positiv.sqlcmd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.com.juja.positiv.sqlcmd.databasemanager.DatabaseManager;
import ua.com.juja.positiv.sqlcmd.service.ConnectionService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by POSITIV on 30.10.2015.
 */
public class MainServlet extends HttpServlet {

    @Autowired
    private ConnectionService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = getAction(request);

        try {
            if (action.equals("/connect")) {
                jsp("connect", request, response);
                return;
            }

            DatabaseManager manager = getManager(request);

            if (manager == null) {
                redirect("connect", response);
                return;
            }

            if (action.equals("/menu") || action.equals("/")) {
                jsp("menu", request, response);

            } else if (action.equals("/help")) {
                jsp("help", request, response);

            } else if (action.equals("/createTable")) {
                table(request, response);

            } else if (action.equals("/table")) {
                jsp("createTable", request, response);

            } else if (action.equals("/list")) {
                list(manager, request, response);

            } else if (action.equals("/find")) {
                jsp("tableName", request, response);

            } else if (action.equals("/clear")) {
                jsp("clear", request, response);

            } else if (action.equals("/delete")) {
                jsp("delete", request, response);

            } else if (action.equals("/create")) {
                request.setAttribute("actionURL", "createRecord");

                jsp("tableName", request, response);

            } else if (action.equals("/createDatabase")) {
                jsp("createDatabase", request, response);

            } else if (action.equals("/deleteDatabase")) {
                jsp("deleteDatabase", request, response);

            } else if (action.equals("/update")) {
                request.setAttribute("actionURL", "updateRecord");

                jsp("tableName", request, response);

            } else {
                jsp("error", request, response);
            }
        } catch (Exception e) {
            error(request, response, e);
        }
    }

    private void table(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tableName = request.getParameter("tableName");
        int columnCount = Integer.parseInt(request.getParameter("columnCount"));

        request.setAttribute("tableName", tableName);
        request.setAttribute("columnCount", columnCount);

        jsp("table", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        DatabaseManager manager = getManager(request);

        try {
            if (action.equals("/connect")) {
                connect(request, response);

            } else if (action.equals("/find")) {
                find(manager, request, response);

            } else if (action.equals("/clear")) {
                clear(manager, request, response);

            } else if (action.equals("/delete")) {
                delete(manager, request, response);

            } else if (action.equals("/createRecord")) {
                prepare(request, manager);

                jsp("create", request, response);

            } else if (action.equals("/create")) {
                create(manager, request, response);

            } else if (action.equals("/createDatabase")) {
                createDatabase(manager, request, response);

            } else if (action.equals("/deleteDatabase")) {
                deleteDatabase(manager, request, response);

            } else if (action.equals("/table")) {
                table(manager, request, response);

            } else if (action.equals("/updateRecord")) {
                prepare(request, manager);

                jsp("update", request, response);

            } else if (action.equals("/update")) {
                update(manager, request, response);
            }
        } catch (Exception e) {
            error(request, response, e);
        }
    }

    private void prepare(HttpServletRequest request, DatabaseManager manager) throws Exception{
        String tableName = request.getParameter("tableName");

        request.setAttribute("columnCount", getColumnCount(manager, tableName));
        request.setAttribute("tableName", tableName);
    }

    private int getColumnCount(DatabaseManager manager, String tableName) throws SQLException {
        return Integer.parseInt(manager.getTableData(tableName).get(0));
    }

    private String getAction(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.substring(request.getContextPath().length(), requestURI.length());
    }

    private void table(DatabaseManager manager, HttpServletRequest request,
                       HttpServletResponse response) throws Exception
    {
        String tableName = request.getParameter("tableName");
        int columnCount = Integer.parseInt(request.getParameter("columnCount"));
        String keyName = request.getParameter("keyName");

        Map<String, Object> columnParameters = new HashMap<>();
        for (int index = 0; index < columnCount; index++) {
            int jindex = index + 1;
            columnParameters.put(request.getParameter("columnName" + jindex),
                    request.getParameter("columnType" + jindex));
        }

        manager.table(tableName, keyName, columnParameters);
        jsp("success", request, response);
    }

    private void update(DatabaseManager manager, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        String tableName = request.getParameter("tableName");
        Map<String, Object> data = new HashMap<>();

        for (int index = 1; index < getColumnCount(manager, tableName); index++) {
            data.put(request.getParameter("columnName" + index),
                    request.getParameter("columnValue" + index));
        }

        String keyName = request.getParameter("keyName");
        String keyValue = request.getParameter("keyValue");
        manager.update(tableName, keyName, keyValue, data);
        jsp("success", request, response);
    }

    private void deleteDatabase(DatabaseManager manager, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        String databaseName = request.getParameter("databaseName");

        manager.drop(databaseName);
        jsp("success", request, response);
    }

    private void createDatabase(DatabaseManager manager, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        String databaseName = request.getParameter("databaseName");

        manager.createBase(databaseName);
        jsp("success", request, response);
    }

    private void create(DatabaseManager manager, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        String tableName = request.getParameter("tableName");
        Map<String, Object> data = new HashMap<>();

        for (int index = 1; index <= getColumnCount(manager, tableName); index++) {
            data.put(request.getParameter("columnName" + index),
                    request.getParameter("columnValue" + index));
        }
        manager.create(tableName, data);
        jsp("success", request, response);
    }

    private void delete(DatabaseManager manager, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        String tableName = request.getParameter("tableName");
        String keyName = request.getParameter("keyName");
        String keyValue = request.getParameter("keyValue");

        manager.delete(tableName, keyName, keyValue);
        jsp("success", request, response);
    }

    private void clear(DatabaseManager manager, HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        String tableName = request.getParameter("tableName");
        manager.clear(tableName);
        jsp("success", request, response);
    }

    private void list(DatabaseManager manager, HttpServletRequest request,
                      HttpServletResponse response) throws Exception {
        Set<String> tableList = manager.getTableNames();
        request.setAttribute("tables", tableList);
        jsp("list", request, response);
    }

    private void find(DatabaseManager manager, HttpServletRequest request,
                      HttpServletResponse response) throws Exception {
        String tableName = request.getParameter("tableName");

        List<String> tableData = manager.getTableData(tableName);

        request.setAttribute("table", getLists(tableData));

        jsp("find", request, response);
    }

    private List<List<String>> getLists(List<String> tableData) {
        List<List<String>> table = new ArrayList<>(tableData.size() - 1);
        int columnCount = Integer.parseInt(tableData.get(0));
        for (int current = 1; current < tableData.size();) {
            List<String> row = new ArrayList<>(columnCount);
            for (int rowIndex = 0; rowIndex < columnCount; rowIndex++) {
                row.add(tableData.get(current++));
            }
            table.add(row);
        }
        return table;
    }

    private void connect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String database = request.getParameter("database");
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        DatabaseManager manager = service.connect(database, user, password);
        request.getSession().setAttribute("manager", manager);
        redirect("menu", response);
    }

    private void error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        request.setAttribute("message", e.getMessage());
        try {
            e.printStackTrace();
//            jsp("error.jsp", request, response);
            jsp("error", request, response);
        } catch (Exception e1) {
            e.printStackTrace();
        }
    }

    private void redirect(String url, HttpServletResponse response) throws IOException {
        response.sendRedirect(response.encodeRedirectURL(url));
    }

    private DatabaseManager getManager(HttpServletRequest request) {
        return (DatabaseManager) request.getSession().getAttribute("manager");
    }

    private void jsp(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(jsp + ".jsp").forward(request, response);
    }
}
