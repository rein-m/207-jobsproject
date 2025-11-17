package use_case.login;

import entity.Entity;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String username);

    void save(Entity entity);

    Entity get(String identifier,  String entityType);

}
