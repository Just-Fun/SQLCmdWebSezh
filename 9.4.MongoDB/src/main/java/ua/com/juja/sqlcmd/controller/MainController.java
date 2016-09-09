package ua.com.juja.sqlcmd.controller;

/**
 * Created by oleksandr.baglai on 11.12.2015.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}
