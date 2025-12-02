package use_case.post_job;

import entity.Company;

import java.util.ArrayList;

public class PostJobInteractor implements PostJobInputBoundary {
    private final PostJobOutputBoundary postJobPresenter;
    private final PostJobUserDataAccessInterface postJobUserDataAccessInterface;

    public PostJobInteractor(PostJobOutputBoundary postJobPresenter,  PostJobUserDataAccessInterface postJobUserDataAccessInterface) {
        this.postJobPresenter = postJobPresenter;
        this.postJobUserDataAccessInterface = postJobUserDataAccessInterface;
    }
    @Override
    public void execute (PostJobInputData postJobInputData) {
        final Company company = postJobUserDataAccessInterface.getCompany(postJobInputData.getIdentifier());
        ArrayList<ArrayList<String>> jobs = company.getJobs();
        ArrayList<String> newJob = new ArrayList<>();
        newJob.add(postJobInputData.getTitle());
        newJob.add(postJobInputData.getDescription());
        newJob.add(postJobInputData.getLocation());
        jobs.add(newJob);
        postJobUserDataAccessInterface.saveData();
//        postJobUserDataAccessInterface.postJob(postJobInputData.getTitle(), postJobInputData.getDescription(), postJobInputData.getLocation());
        final PostJobOutputData postJobOutputData = new PostJobOutputData(postJobInputData.getTitle(), postJobInputData.getDescription(), postJobInputData.getLocation());
        postJobPresenter.updateCompanyLoggedInState(postJobOutputData);
    }
    @Override
    public void switchToCompanyLoggedInView() {
        postJobPresenter.switchToCompanyLoggedInView(); }
}
