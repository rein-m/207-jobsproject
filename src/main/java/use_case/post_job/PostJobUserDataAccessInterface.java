package use_case.post_job;

public interface PostJobUserDataAccessInterface {
    //postJob saves job info into company account

    void postJob(String title, String description);

    void save(String title, String description);
}
