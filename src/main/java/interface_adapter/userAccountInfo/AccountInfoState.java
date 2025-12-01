package interface_adapter.userAccountInfo;

import java.util.ArrayList;

public class AccountInfoState {

    private String userIdentifier;
    private String location;
    private String email;
    private String phone;
    private ArrayList<String> resumes;

    public String getLocation() {return location;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public String getUserIdentifier() {return userIdentifier;}
    public ArrayList<String> getResumes() {return resumes;}
    public void setLocation(String location) {this.location = location;}
    public void setEmail(String email) {this.email = email;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setUserIdentifier(String userIdentifier) {this.userIdentifier = userIdentifier;}
    public void setResumes(ArrayList<String> resumes) {this.resumes = resumes;}


}
