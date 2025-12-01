package use_case.AccountInfo;

import entity.User;

import java.util.ArrayList;

public class AccountInfoOutputData {

    private final String location;
    private final String email;
    private final String phone;
//    private final ArrayList<Entity> resumes;

    public AccountInfoOutputData(User user) {
        this.location = user.getLocation();
        this.email = user.getEmail();
        this.phone = user.getPhone();
//        this.resumes = user.getResumes();
    }

    public String getLocation() {return location;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
//    public ArrayList<Entity> getResumes() {return resumes;}


}
