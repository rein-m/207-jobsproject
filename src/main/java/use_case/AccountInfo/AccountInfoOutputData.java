package use_case.AccountInfo;

import entity.User;

import java.util.ArrayList;

public class AccountInfoOutputData {

    private final String location;
    private final String email;
    private final String phone;
    private final ArrayList<String> resumes;
    private final String identifier;

    public AccountInfoOutputData(String location, String email, String phone, ArrayList<String> resumes, String identifier) {
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.resumes = resumes;
        this.identifier = identifier;
    }

    public String getLocation() {return location;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public ArrayList<String> getResumes() {return resumes;}
    public String getIdentifier() {return identifier;}


}
