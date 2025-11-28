package entity;

public class Company extends User implements Entity {
    private static String name = "";
    private final String password;
    public Company(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }
    public static String getIdentifier() {
        return name;
    }
    public String getPassword() {
        return password;
    }
}
