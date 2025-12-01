package use_case.edit_company_account;

import interface_adapter.edit_company_account.EditCompanyAccountPresenter;

public class EditCompanyAccountInteractor implements EditCompanyAccountInputBoundary {
    private final EditCompanyAccountOutputBoundary editCompanyAccountPresenter;
//    private final EditCompanyAccountDataAccessInterface editCompanyAccountDataAccessInterface;

    public EditCompanyAccountInteractor(EditCompanyAccountOutputBoundary editCompanyAccountPresenter) {
        this.editCompanyAccountPresenter = editCompanyAccountPresenter;
    }

    public void execute(EditCompanyAccountInputData editCompanyAccountInputData) {
//        editCompanyAccountDataAccessInterface
        final EditCompanyAccountOutputData editCompanyAccountOutputData =
                new EditCompanyAccountOutputData(editCompanyAccountInputData.getName()
                        , editCompanyAccountInputData.getWebsite(), editCompanyAccountInputData.getEmail()
                        , editCompanyAccountInputData.getNumber(), editCompanyAccountInputData.getLocation());
        editCompanyAccountPresenter.updateCompanyAccountState(editCompanyAccountOutputData);
    }
}
