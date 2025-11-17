package interface_adapter.post_job;

import interface_adapter.ViewManagerModel;
import interface_adapter.company_loggedin.CompanyLoggedInState;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;
import use_case.post_job.PostJobOutputBoundary;
import use_case.post_job.PostJobOutputData;

public class PostJobPresenter implements PostJobOutputBoundary {
    private final CompanyLoggedInViewModel companyLoggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public PostJobPresenter(CompanyLoggedInViewModel companyLoggedInViewModel, ViewManagerModel viewManagerModel) {
        this.companyLoggedInViewModel = companyLoggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToCompanyLoggedInView() {
        viewManagerModel.setState(companyLoggedInViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    //need something to update the state of companyloggedinviewmodel (append new job to jobs list)
    public void updateCompanyLoggedInState(PostJobOutputData postJobOutputData) {
        final CompanyLoggedInState state = companyLoggedInViewModel.getState();
        state.addJobListing(postJobOutputData.getJobTitle(), postJobOutputData.getJobDescription());
        companyLoggedInViewModel.setState(state);
        companyLoggedInViewModel.firePropertyChange();
    }
}
