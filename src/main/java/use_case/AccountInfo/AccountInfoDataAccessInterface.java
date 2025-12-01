package use_case.AccountInfo;

import entity.User;

import java.sql.SQLException;

public interface AccountInfoDataAccessInterface {
    // no purpose, just added so that intelliJ stops getting mad at me.
    void save(User user);

    // same. Want intelliJ to stop yapping at me. Don't use.
    User get(String identifier, String entityType);

    User get(String username) throws SQLException;

    // not sure what to do with this postJob method...?
    void postJob(String title, String description);
}
