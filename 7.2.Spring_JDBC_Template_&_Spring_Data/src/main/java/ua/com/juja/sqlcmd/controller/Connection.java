package ua.com.juja.sqlcmd.controller;

/**
 * Created by oleksandr.baglai on 11.12.2015.
 */
public class Connection {

    private String userName;
    private String password;
    private String dbName;
    private String fromPage;

    public Connection() {
        // do nothing
    }

    public Connection(String page) {
        this.fromPage = page;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDbName() {
        return dbName;
    }

    public String getFromPage() {
        return fromPage;
    }
}
