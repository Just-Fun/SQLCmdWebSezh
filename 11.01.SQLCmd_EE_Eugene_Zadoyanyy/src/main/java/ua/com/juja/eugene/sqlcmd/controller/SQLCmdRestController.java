package ua.com.juja.eugene.sqlcmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.juja.eugene.sqlcmd.model.databasemanager.DatabaseManager;
import ua.com.juja.eugene.sqlcmd.service.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class SQLCmdRestController {

    private final static String DATABASE_MANAGER_TAG = "db_manager";

    @Autowired
    private Service service;

    @RequestMapping(value = "/actions/{username}/content", method = RequestMethod.GET)
    public List<UserActionLog> actions(@PathVariable("username") String username) {
        return service.findActionsByUsername(username);
    }

    @RequestMapping(value = "/databases/content", method = RequestMethod.GET)
    public Set<String> databases(HttpSession session) {
        return getManager(session).getDatabases();
    }

    @RequestMapping(value = "/server/connect/{database}", method = RequestMethod.GET)
    public void connectToDatabase(HttpSession session, @PathVariable(value = "database") String database) {
        service.connectToDatabase(getManager(session), database);
    }

    @RequestMapping(value = "/tables/content", method = RequestMethod.GET)
    public Set<String> tables(HttpSession session) {
        return getManager(session).getTableNames();
    }

    @RequestMapping(value = "/table/{table}/columns/content")
    public Set<String> tableColumns(HttpSession session, @PathVariable("table") String table){
        return getManager(session).getTableColumns(table);
    }

    @RequestMapping(value = "/database/table/{table}/content", method = RequestMethod.GET)
    public List<List<String>> getTable(HttpSession session, @PathVariable("table") String table) {
        return service.getTableData(getManager(session), table);
    }

    @RequestMapping(value = {"/", "/login", "/main"}, method = RequestMethod.POST)
    public String login(HttpSession session, @ModelAttribute("connection") Connection connection) {
        try {
            DatabaseManager manager = service.connectToServer(connection.getUsername(), connection.getPassword());
            session.setAttribute(DATABASE_MANAGER_TAG, manager);
            return null;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/create/database/{database}", method = RequestMethod.POST)
    public String createDatabase(HttpSession session, @PathVariable("database") String database) {
        try {
            service.createDatabase(getManager(session), database);
            return null;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/server/drop/database/{database}", method = RequestMethod.GET)
    public void dropDatabase(HttpSession session, @PathVariable("database") String database) {
        service.dropDatabase(getManager(session), database);
    }

    @RequestMapping(value = "/database/disconnect", method = RequestMethod.GET)
    public void disconnect(HttpSession session) {
        service.disconnectFromDatabase(getManager(session));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpSession session) {
        session.removeAttribute(DATABASE_MANAGER_TAG);
    }

    @RequestMapping(value = "/database/clear/table/{table}", method = RequestMethod.GET)
    public void clearTable(HttpSession session, @PathVariable(value = "table") String table) {
        service.clearTable(getManager(session), table);
    }

    @RequestMapping(value = "/database/create/entry/{table}", method = RequestMethod.POST)
    public String createEntry(HttpSession session,
                              @PathVariable(value = "table") String table,
                              @RequestParam(value = "values[]") String[] values)
    {
        try {
            service.createEntry(getManager(session), table, values);
            return null;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/database/create/table", method = RequestMethod.POST)
    public String createTable(HttpSession session, @RequestParam("query") String query) {
        try {
            service.createTable(getManager(session), query);
            return null;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/database/drop/table/{table}", method = RequestMethod.GET)
    public void dropTable(HttpSession session, @PathVariable(value = "table") String table) {
        service.dropTable(getManager(session), table);
    }

    @RequestMapping(value = "/database/update/record/{table}", method = RequestMethod.POST)
    public String updateRecord(HttpSession session,
                               @PathVariable(value = "table") String table,
                               @RequestParam(value = "values[]") String[] values)
    {
        try {
            service.updateRecord(getManager(session), table, values);
            return null;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private DatabaseManager getManager(HttpSession session) {
        return (DatabaseManager) session.getAttribute(DATABASE_MANAGER_TAG);
    }
}
