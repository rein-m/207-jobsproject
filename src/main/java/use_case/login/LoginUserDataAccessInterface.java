package use_case.login;

import entity.Entity;

public interface LoginUserDataAccessInterface {
    boolean existsByIdentifier(String identifier);

    void save(Entity entity);

    Entity get(String identifier,  String entityType);

}
