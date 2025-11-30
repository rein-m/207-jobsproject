package use_case.login;

import entity.Entity;

public interface LoginCompanyDataAccessInterface {

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
     * Retrieves the Company entity.
     * @param identifier the username to look up.
     * @return the Company entity.
     */
    Entity get(String identifier);
}