package entity;
import java.util.HashMap;
import java.util.Map;

public class User implements Entity {
    private String name;
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
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


}
