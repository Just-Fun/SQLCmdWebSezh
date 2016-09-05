package ua.com.juja.eugene.sqlcmd.model.repository;

public interface UserActionRepositoryCustom {

    void createAction(String username, String database, String action);
}
