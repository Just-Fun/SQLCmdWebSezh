package ua.com.juja.sqlcmd.model;

import org.springframework.data.repository.CrudRepository;
import ua.com.juja.sqlcmd.model.entity.UserAction;

import java.util.List;

/**
 * Created by oleksandr.baglai on 19.12.2015.
 */
public interface UserActionRepository extends CrudRepository<UserAction, Integer> {
    List<UserAction> findByUserName(String userName);
}
