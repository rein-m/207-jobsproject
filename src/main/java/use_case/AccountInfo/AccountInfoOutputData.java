package use_case.AccountInfo;

import entity.User;

import java.util.ArrayList;

public class AccountInfoOutputData {

    private final String location;
    private final String email;
    private final String phone;
//    private final ArrayList<Entity> resumes;

    public AccountInfoOutputData(String location, String email, String phone) {
        this.location = location;
        this.email = email;
        this.phone = phone;
//        this.resumes = user.getResumes();
    }

    public String getLocation() {return location;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
//    public ArrayList<String> getResumes() {return resumes;}


}
