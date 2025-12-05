package use_case.account;

import entity.User;

/**
 * Data access boundary for editing user accounts.
 * Implemented in the data layer.
 */
public interface EditAccountUserDataAccessInterface {

    boolean existsByIdentifier(String identifier);

    User get(String identifier);

    void save(User user);
}
