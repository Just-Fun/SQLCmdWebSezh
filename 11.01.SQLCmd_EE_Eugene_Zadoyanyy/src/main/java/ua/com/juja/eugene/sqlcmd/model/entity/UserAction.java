package ua.com.juja.eugene.sqlcmd.model.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "actions", schema = "public")
public class UserAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "action")
    private String action;

    @Column(name = "date")
    private String date;

    @JoinColumn(name = "database_connection_id")
    @ManyToOne(fetch = FetchType.EAGER)
    DatabaseConnection databaseConnection;

    public UserAction() {
        //do nothing
    }

    public UserAction(String action, long currentTime, DatabaseConnection databaseConnection) {
        this.action = action;
        this.date = formatDate(currentTime);
        this.databaseConnection = databaseConnection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    private String formatDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return dateFormat.format(date);
    }
}
