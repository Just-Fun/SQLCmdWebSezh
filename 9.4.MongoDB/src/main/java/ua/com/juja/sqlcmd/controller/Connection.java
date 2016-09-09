package ua.com.juja.sqlcmd.controller;

/**
 * Created by oleksandr.baglai on 11.12.2015.
 */
public class Connection {

    private String userName;
    private String password;
    private String database;

    public Connection() {
        // do nothing
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

}
