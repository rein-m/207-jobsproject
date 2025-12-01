package interface_adapter.edit_company_account;

import interface_adapter.company_account.CompanyAccountState;
import interface_adapter.company_account.CompanyAccountViewModel;
import use_case.edit_company_account.EditCompanyAccountOutputBoundary;
import use_case.edit_company_account.EditCompanyAccountOutputData;

public class EditCompanyAccountPresenter implements EditCompanyAccountOutputBoundary {
    private final CompanyAccountViewModel companyAccountViewModel;

    public EditCompanyAccountPresenter(CompanyAccountViewModel companyAccountViewModel) {
        this.companyAccountViewModel = companyAccountViewModel;
    }

    public  void updateCompanyAccountState(EditCompanyAccountOutputData editCompanyAccountOutputData) {
        final CompanyAccountState state = companyAccountViewModel.getState();
        state.setCompanyName(editCompanyAccountOutputData.getName());
        state.setWebsite(editCompanyAccountOutputData.getWebsite());
        state.setEmail(editCompanyAccountOutputData.getEmail());
        state.setNumber(editCompanyAccountOutputData.getNumber());
        state.setLocation(editCompanyAccountOutputData.getLocation());
    }
}
