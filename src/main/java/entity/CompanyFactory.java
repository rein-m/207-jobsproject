package entity;

import java.util.ArrayList;

public class CompanyFactory {
    public static Company createEntity(String identifier, String companyName, String website, String email, String number,
                                       String location, String password, ArrayList<ArrayList<String>> jobs) {
        return new Company(identifier, companyName, website, email, number, location, password, jobs);
    }
}