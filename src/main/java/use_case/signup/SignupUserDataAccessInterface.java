package use_case.signup;

import entity.Entity;

public interface SignupUserDataAccessInterface {

    /**
     * Checks if the given identifier (username) exists in the User database.
     * @param identifier the identifier to look for
     * @return true if a User with the given identifier exists; false otherwise
     */
    boolean existsByIdentifier(String identifier);

    /**
     * Saves the User to the database.
     * @param user the User entity to save.
     */
    void save(Entity user);
}