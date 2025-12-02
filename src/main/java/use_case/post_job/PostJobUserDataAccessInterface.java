package use_case.post_job;

import entity.Company;
import entity.Entity;

public interface PostJobUserDataAccessInterface {
    //postJob saves job info into company account

//    void postJob(String title, String description);
//
//    void save(String title, String description);

    public Company getCompany(String name);

    void saveData();
}

