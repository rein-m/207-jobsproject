package entity;

public class User implements Entity {
    private final String name;
    private final String password;
    private String location;
    private String email;
    private String phone;

    public User(String name, String password, String location, String email, String phone) {
        this.name = name;
        this.password = password;
        this.location = location;
        this.email = email;
        this.phone = phone;
    }

    public String getIdentifier() {
        return name;
    }
    public String getPassword() {
        return password;
    }
}
