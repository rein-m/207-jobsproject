package use_case.edit_company_account;

import entity.Company;
import interface_adapter.edit_company_account.EditCompanyAccountPresenter;

public class EditCompanyAccountInteractor implements EditCompanyAccountInputBoundary {
    private final EditCompanyAccountOutputBoundary editCompanyAccountPresenter;
    private final EditCompanyAccountDataAccessInterface editCompanyAccountDataAccessInterface;

    public EditCompanyAccountInteractor(EditCompanyAccountOutputBoundary editCompanyAccountPresenter,  EditCompanyAccountDataAccessInterface editCompanyAccountDataAccessInterface) {
        this.editCompanyAccountPresenter = editCompanyAccountPresenter;
        this.editCompanyAccountDataAccessInterface = editCompanyAccountDataAccessInterface;
    }

    public void execute(EditCompanyAccountInputData editCompanyAccountInputData) {
        final Company company = editCompanyAccountDataAccessInterface.getCompany(editCompanyAccountInputData.getIdentifier());
        company.setCompanyName(editCompanyAccountInputData.getName());
        company.setWebsite(editCompanyAccountInputData.getWebsite());
        company.setEmail(editCompanyAccountInputData.getEmail());
        company.setNumber(editCompanyAccountInputData.getNumber());
        company.setLocation(editCompanyAccountInputData.getLocation());
        editCompanyAccountDataAccessInterface.saveData();

        final EditCompanyAccountOutputData editCompanyAccountOutputData =
                new EditCompanyAccountOutputData(editCompanyAccountInputData.getName()
                        , editCompanyAccountInputData.getWebsite(), editCompanyAccountInputData.getEmail()
                        , editCompanyAccountInputData.getNumber(), editCompanyAccountInputData.getLocation());
        editCompanyAccountPresenter.updateCompanyAccountState(editCompanyAccountOutputData);
    }
}
