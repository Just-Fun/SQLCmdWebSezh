package ua.com.juja.sqlcmd.model;

import org.springframework.data.repository.CrudRepository;
import ua.com.juja.sqlcmd.model.entity.UserActions;

import java.util.List;

/**
 * Created by serzh on 6/24/16.
 */
public interface UserActionsRepository extends CrudRepository<UserActions, Integer> {

    List<UserActions> findByUserName(String userName);
}
