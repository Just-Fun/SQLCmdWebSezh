package ua.com.juja.eugene.sqlcmd.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.juja.eugene.sqlcmd.model.entity.UserAction;

import java.util.List;

public interface UserActionRepository extends CrudRepository<UserAction, Integer>, UserActionRepositoryCustom {

    @Query(value = "SELECT ua FROM UserAction ua WHERE ua.databaseConnection.username = :username")
    List<UserAction> findByUsername(@Param("username") String username);
}
