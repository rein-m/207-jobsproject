package use_case.post_job;

public class PostJobInteractor implements PostJobInputBoundary {
    private final PostJobOutputBoundary postJobPresenter;
//    private final PostJobUserDataAccessInterface postJobUserDataAccessInterface;

//    public PostJobInteractor(PostJobOutputBoundary postJobPresenter,  PostJobUserDataAccessInterface postJobUserDataAccessInterface) {
    public PostJobInteractor(PostJobOutputBoundary postJobPresenter) {
        this.postJobPresenter = postJobPresenter;
//        this.postJobUserDataAccessInterface = postJobUserDataAccessInterface;
    }
    @Override
    public void execute (PostJobInputData postJobInputData) {
//        postJobUserDataAccessInterface.postJob(postJobInputData.getTitle(), postJobInputData.getDescription(), postJobInputData.getLocation());
        final PostJobOutputData postJobOutputData = new PostJobOutputData(postJobInputData.getTitle(), postJobInputData.getDescription(), postJobInputData.getLocation());
        postJobPresenter.updateCompanyLoggedInState(postJobOutputData);
    }
    @Override
    public void switchToCompanyLoggedInView() {
        postJobPresenter.switchToCompanyLoggedInView(); }
}
