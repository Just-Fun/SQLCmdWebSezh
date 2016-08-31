package ua.com.juja.sqlcmd.controller.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleksandr.baglai on 09.12.2015.
 */
public interface Action {
    void get(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

    boolean canProcess(String url);

    void post(HttpServletRequest req, HttpServletResponse resp);
}
