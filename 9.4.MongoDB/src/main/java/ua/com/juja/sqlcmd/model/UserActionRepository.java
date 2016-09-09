package ua.com.juja.sqlcmd.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.com.juja.sqlcmd.model.entity.UserAction;

import java.util.List;

/**
 * Created by oleksandr.baglai on 19.12.2015.
 */
public interface UserActionRepository extends MongoRepository<UserAction, Integer>, UserActionRepositoryCustom {

    @Query("{'connection.$id': ?0}")
    List<UserAction> findUserActionsForConnection(ObjectId id);

}
