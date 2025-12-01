package use_case.login;

import entity.Entity;

public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given identifier exists.
     * @param identifier the username to check.
     * @return true if exists.
     */
    boolean existsByIdentifier(String identifier);

    /**
     * Retrieves the stored password for the given identifier.
     * @param identifier the username to check.
     * @return the password string.
     */
    String getPassword(String identifier);

    /**
     * Retrieves the User entity.
     * @param identifier the username to look up.
     * @return the User entity.
     */
    Entity get(String identifier);
}