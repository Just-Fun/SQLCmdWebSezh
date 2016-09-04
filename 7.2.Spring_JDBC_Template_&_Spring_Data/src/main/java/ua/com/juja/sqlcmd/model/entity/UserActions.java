package ua.com.juja.sqlcmd.model.entity;

import javax.persistence.*;

/**
 * Created by serzh on 6/24/16.
 */
@Entity
@Table(name = "user_actions", schema = "public")
public class UserActions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "db_name")
    private String dbName;

    private String action;

    public UserActions() {
        // do nothing
    }

    public UserActions(String action, String dbName, String userName) {
        this.action = action;
        this.dbName = dbName;
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setIs(int id) {
        this.id = id;
    }
}
