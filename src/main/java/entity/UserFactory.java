package entity;

import java.util.ArrayList;

public class UserFactory {
    public User createEntity(String name, String username, String password, String location,
                             String email, String phone, ArrayList<Entity> resumes) {
        return new User(name, username, password, location, email, phone, resumes);
    }
}
