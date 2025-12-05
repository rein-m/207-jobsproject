package entity;

public class UserFactory {
    public static User createEntity(String name, String username, String password, String location, String email, String phone) {
        return new User(name, username, password, location, email, phone);
    }
}
