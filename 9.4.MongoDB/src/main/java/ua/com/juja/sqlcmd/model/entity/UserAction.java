package ua.com.juja.sqlcmd.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by oleksandr.baglai on 19.12.2015.
 */
@Document(collection = "userAction")
public class UserAction {

    @Id
    private String id;
    private String action;

    @DBRef(db = "databaseConnection")
    private DatabaseConnection connection;

    public UserAction() {
        // do nothing
    }

    @PersistenceConstructor
    public UserAction(String action, DatabaseConnection connection) {
        this.action = action;
        this.connection = connection;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DatabaseConnection getConnection() {
        return connection;
    }

    public void setConnection(DatabaseConnection connection) {
        this.connection = connection;
    }
}
