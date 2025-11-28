package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String username);

    void save();

    // Save the current user.
    //-- no need to override, since this is different function than the save()
    public void save(User user);

    User get(String identifier, String entityType);

    // get a user.
    String get(String identifier);
}
