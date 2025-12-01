package entity;

public class UserFactory {
<<<<<<< HEAD
    public static User createEntity(String name, String username, String password, String location, String email, String phone) {
        return new User(name, username, password, location, email, phone);
=======
    public User createEntity(String name, String username, String password, String location,
                             String email, String phone, ArrayList<String> resumes) {
        return new User(name, username, password, location, email, phone, resumes);
>>>>>>> 4e32c2b81d7ea2169b0d780b77202124afeee428
    }
}
