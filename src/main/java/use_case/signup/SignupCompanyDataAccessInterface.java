package use_case.signup;

import entity.Entity;

public interface SignupCompanyDataAccessInterface {

    /**
     * Checks if the given identifier (username) exists in the Company database.
     * @param identifier the identifier to look for
     * @return true if a Company with the given identifier exists; false otherwise
     */
    boolean existsByIdentifier(String identifier);

    /**
     * Saves the Company to the database.
     * @param company the Company entity to save.
     */
    void save(Entity company);
}