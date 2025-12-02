package interface_adapter.pull_company_data;


import interface_adapter.ViewManagerModel;
import interface_adapter.company_account.CompanyAccountState;
import interface_adapter.company_account.CompanyAccountViewModel;
import use_case.pull_company_data.PullCompanyDataOutputBoundary;
import use_case.pull_company_data.PullCompanyDataOutputData;

public class PullCompanyDataPresenter implements PullCompanyDataOutputBoundary {
    private final CompanyAccountViewModel companyAccountViewModel;

    public PullCompanyDataPresenter(CompanyAccountViewModel companyAccountViewModel) {
        this.companyAccountViewModel = companyAccountViewModel;
    }

    public void updateCompanyAccountState(PullCompanyDataOutputData pullCompanyDataOutputData) {
        final CompanyAccountState state = companyAccountViewModel.getState();
        state.setCompanyName(pullCompanyDataOutputData.getCompanyName());
        state.setEmail(pullCompanyDataOutputData.getEmail());
        state.setLocation(pullCompanyDataOutputData.getLocation());
        state.setNumber(pullCompanyDataOutputData.getNumber());
        state.setWebsite(pullCompanyDataOutputData.getWebsite());
        companyAccountViewModel.setState(state);
        companyAccountViewModel.firePropertyChange();
    }
}
