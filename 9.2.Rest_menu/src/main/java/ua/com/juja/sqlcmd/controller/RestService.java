package ua.com.juja.sqlcmd.controller;

/**
 * Created by oleksandr.baglai on 11.12.2015.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.juja.sqlcmd.service.Service;

import java.util.List;

@RestController
public class RestService {

    @Autowired
    private Service service;

    @RequestMapping(value = "/menu/content", method = RequestMethod.GET)
    public List<String> menuItems() {
        return service.commandsList();
    }

    @RequestMapping(value = "/help/fun", method = RequestMethod.GET)
    public String justFun() {
        return "Hi there! :)";
    }

}
