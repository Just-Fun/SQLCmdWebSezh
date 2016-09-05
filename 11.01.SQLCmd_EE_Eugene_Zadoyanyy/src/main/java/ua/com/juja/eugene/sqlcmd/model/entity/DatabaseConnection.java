package ua.com.juja.eugene.sqlcmd.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "database_connection", schema = "public")
public class DatabaseConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "database")
    private String database;

    @JoinColumn(name = "database_connection_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<UserAction> userActions;

    public DatabaseConnection() {
        //do nothing
    }

    public DatabaseConnection(String username, String database) {
        this.username = username;
        this.database = database;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public List<UserAction> getUserActions() {
        return userActions;
    }

    public void setUserActions(List<UserAction> userActions) {
        this.userActions = userActions;
    }
}
