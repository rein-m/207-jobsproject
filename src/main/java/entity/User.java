package entity;

import java.util.ArrayList;

public class User implements Entity {
    private final String name;
    private final String username;
    private final String password;
    private String location;
    private String email;
    private String phone;
    private ArrayList<Entity> resumes;

    public User(String name, String username, String password, String location, String email,
                String phone, ArrayList<Entity> resumes) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.resumes = resumes;
    }




    public String getIdentifier() {
        return name;
    }
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getLocation() {return location;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public ArrayList<Entity> getResumes() {return resumes;}
}