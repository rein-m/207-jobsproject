package use_case.login;

import entity.Company;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String username);

    void save();

    // Save the current user.
    //-- no need to override, since this is different function than the save()
    public void save(Company company);

    Company get(String identifier, String entityType);

    // get a user.
    Company get(String identifier);
}
