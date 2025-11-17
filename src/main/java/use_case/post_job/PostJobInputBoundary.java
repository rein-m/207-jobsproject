package use_case.post_job;

public interface PostJobInputBoundary {
    void execute(PostJobInputData postJobInputData);

    void switchToCompanyLoggedInView();
}
