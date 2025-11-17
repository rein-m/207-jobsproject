package entity;

public class UserFactory {
    public User createEntity(String name, String password) {
        return new User(name, password);
    }
}
