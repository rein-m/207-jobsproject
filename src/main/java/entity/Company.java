package entity;

public class Company implements Entity {
    private final String name;
    private final String password;
    public Company(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getIdentifier() {
        return name;
    }
    public String getPassword() {
        return password;
    }
}
