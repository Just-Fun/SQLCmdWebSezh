package ua.com.juja.sqlcmd.model;

/**
 * Created by oleksandr.baglai on 26.12.2015.
 */
public interface UserActionRepositoryCustom {

    void createAction(String databaseName, String userName, String action);

}
