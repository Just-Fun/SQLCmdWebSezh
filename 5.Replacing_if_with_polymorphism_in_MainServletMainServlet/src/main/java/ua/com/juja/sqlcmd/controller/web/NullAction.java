package ua.com.juja.sqlcmd.controller.web;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oleksandr.baglai on 09.12.2015.
 */
public class NullAction implements Action {

    @Override
    public void get(HttpServletRequest req, HttpServletResponse resp) {
        // do nothing
    }

    @Override
    public boolean canProcess(String url) {
        return false;
    }

    @Override
    public void post(HttpServletRequest req, HttpServletResponse resp) {

    }
}
