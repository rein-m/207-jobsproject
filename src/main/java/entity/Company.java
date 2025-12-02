package entity;

import java.util.ArrayList;

public class Company implements Entity {
    private final String identifier;
    private String companyName;
    private String website;
    private String email;
    private String number;
    private String location;
    private String password;
    private ArrayList<ArrayList<String>> jobs;

    public Company(String identifier, String companyName, String website, String email, String number, String location,
                   String password, ArrayList<ArrayList<String>> jobs) {
        this.identifier = identifier;
        this.companyName = companyName;
        this.website = website;
        this.email = email;
        this.number = number;
        this.location = location;
        this.password = password;
        this.jobs = jobs;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<ArrayList<String>> getJobs() {
        return jobs;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
