package interface_adapter.post_job;

import use_case.post_job.PostJobInputBoundary;
import use_case.post_job.PostJobInputData;

public class PostJobController {
    private final PostJobInputBoundary postJobUseCaseInteractor;

    public PostJobController(PostJobInputBoundary postJobUseCaseInteractor) {
        this.postJobUseCaseInteractor = postJobUseCaseInteractor; }

    public void execute(String identifier, String jobTitle, String jobDescription, String jobLocation) {
        //creates an input data that can be used by the use case interactor(postjobinteractor)
        final PostJobInputData postJobInputData = new PostJobInputData(identifier, jobTitle, jobDescription, jobLocation);
        //calls method execute on an input boundary object so that use case interactor can override execute method
        postJobUseCaseInteractor.execute(postJobInputData);
    }

    public void switchToCompanyLoggedInView(){
        postJobUseCaseInteractor.switchToCompanyLoggedInView();
    }
}
