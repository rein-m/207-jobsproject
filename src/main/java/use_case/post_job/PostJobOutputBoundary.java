package use_case.post_job;

public interface PostJobOutputBoundary {
    void switchToCompanyLoggedInView() ;
    void updateCompanyLoggedInState(PostJobOutputData postJobOutputData);
}
