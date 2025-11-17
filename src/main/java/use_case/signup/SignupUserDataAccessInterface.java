package use_case.signup;

import entity.Entity;

public interface SignupUserDataAccessInterface {
    boolean existsbyname(String name);

    void save(Entity entity);
}
