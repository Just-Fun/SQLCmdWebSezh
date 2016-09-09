package ua.com.juja.sqlcmd.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by oleksandr.baglai on 19.12.2015.
 */
@Document(collection = "databaseConnection")
public class DatabaseConnection {

    @Id
    private String id;
    private String userName;
    private String database;

    public DatabaseConnection() {
        // do nothing
    }

    @PersistenceConstructor
    public DatabaseConnection(String userName, String database) {
        this.userName = userName;
        this.database = database;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
