package ua.com.juja.eugene.sqlcmd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SQLCmdController {

    @RequestMapping(value = {"/", "/login", "/main"}, method = RequestMethod.GET)
    public String login() {
        return "main";
    }
}
