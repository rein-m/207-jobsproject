package entity;

public class UserFactory {
    public User createEntity(String name, String password, String location, String email, String phone) {
        return new User(name, password, location, email, phone);
    }
}
