package use_case.signup;

import entity.Entity;

public interface SignupUserDataAccessInterface {

    /**
     * Checks if the given identifier/username exists.
     * @param name the identifier to look for
     * @return true if a user with the given username/identifier exists; false otherwise
     */
    boolean existsByIdentifier(String name);

    /**
     * Saves the customer (user/company)
     * @param Entity entity -- the entity to save.
     */
    void save(Entity entity); // saves entity

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */

    Entity get(String identifier) ; // Returns the entity (user/company) identifier.

}
