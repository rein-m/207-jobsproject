package use_case.post_job;

public class PostJobInteractor implements PostJobInputBoundary {
    private final PostJobOutputBoundary postJobPresenter;
    private final PostJobUserDataAccessInterface postJobUserDataAccessInterface;

    public PostJobInteractor(PostJobOutputBoundary postJobPresenter,  PostJobUserDataAccessInterface postJobUserDataAccessInterface) {
        this.postJobPresenter = postJobPresenter;
        this.postJobUserDataAccessInterface = postJobUserDataAccessInterface;
    }
    @Override
    public void execute (PostJobInputData postJobInputData) {
//        postJobUserDataAccessInterface.postJob(postJobInputData.getTitle(), postJobInputData.getDescription());
        final PostJobOutputData postJobOutputData = new PostJobOutputData(postJobInputData.getTitle(), postJobInputData.getDescription());
        postJobPresenter.updateCompanyLoggedInState(postJobOutputData);
    }
    @Override
    public void switchToCompanyLoggedInView() {
        postJobPresenter.switchToCompanyLoggedInView(); }
}
