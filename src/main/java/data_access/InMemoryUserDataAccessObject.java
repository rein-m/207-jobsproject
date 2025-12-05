package data_access;

import entity.User;
import use_case.account.EditAccountUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple in-memory implementation of the user data access interface.
 */
public class InMemoryUserDataAccessObject implements EditAccountUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    /**
     * Convenience method to seed users for testing.
     */
    public void addUser(User user) {
        users.put(user.getIdentifier(), user);
    }

    @Override
    public boolean existsByIdentifier(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public User get(String identifier) {
        return users.get(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getIdentifier(), user);
    }
}
