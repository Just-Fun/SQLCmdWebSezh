package ua.com.juja.sqlcmd.controller;

/**
 * Created by oleksandr.baglai on 11.12.2015.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.entity.Student;
import ua.com.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private Service service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/menu";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect(HttpSession session, Model model) {
        String page = (String) session.getAttribute("from-page");
        session.removeAttribute("from-page");
        model.addAttribute("connection", new Connection(page));

        if (getManager(session) == null) {
            return "connect";
        } else {
            return "menu";
        }
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connecting(@ModelAttribute("connection") Connection connection,
                             HttpSession session, Model model)
    {
        try {
            DatabaseManager manager = service.connect(connection.getDbName(),
                    connection.getUserName(), connection.getPassword());
            session.setAttribute("db_manager", manager);
            return "redirect:" + connection.getFromPage();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/tables/{table}", method = RequestMethod.GET)
    public String tables(Model model,
                       @PathVariable(value = "table") String table,
                       HttpSession session) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            session.setAttribute("from-page", "/tables/" + table);
            return "redirect:/connect";
        }

        model.addAttribute("table", service.find(manager, table));

        return "find";
    }

    @RequestMapping(value = "/actions/{userName}", method = RequestMethod.GET)
    public String actions(Model model,
                         @PathVariable(value = "userName") String userName) {
        model.addAttribute("actions", service.getAllFor(userName));

        return "actions";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu(Model model) {
        model.addAttribute("items", service.commandsList());
        return "menu";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, HttpSession session) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            session.setAttribute("from-page", "/list");
            return "redirect:/connect";
        }

        model.addAttribute("list", service.tables(manager));
        return "list";
    }

    @RequestMapping(value = "/databases", method = RequestMethod.GET)
    public String databases(Model model, HttpSession session) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            session.setAttribute("from-page", "/databases");
            return "redirect:/connect";
        }

        model.addAttribute("databases", manager.getDatabasesNames());// or service.databases(manager)
        return "databases";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printHello(Model model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("student", "command", new Student());
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("SpringWeb")Student student,
                             ModelMap model) {
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        service.createStudent(student.getName(), student.getAge());
        return "result";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String students(Model model) {
        model.addAttribute("students", service.getAllForStudent());
        return "students";
    }

    @RequestMapping(value = "/createDatabase", method = RequestMethod.GET)
    public ModelAndView createDatabase(HttpSession session) {
        DatabaseManager manager = getManager(session);
        if (manager == null) {
            session.setAttribute("from-page", "/createDatabase");
            return new ModelAndView("redirect:/connect");
        }
        return new ModelAndView("createdatabase", "command", new Base());
    }

    @RequestMapping(value = "/creatingDatabase", method = RequestMethod.POST)
    public String creatingDatabase(@ModelAttribute("SpringWeb") Base base, ModelMap model, HttpSession session) {
        DatabaseManager manager = getManager(session);
        String name = base.getName();
        manager.createDatabase(name);
        model.addAttribute("name", name);
        return "database";
    }


    private DatabaseManager getManager(HttpSession session) {
        return (DatabaseManager) session.getAttribute("db_manager");
    }

    // TODO со странички http://localhost:8080/sqlcmd/tables/user
    // мереходим на menu то видим ошибку
}
