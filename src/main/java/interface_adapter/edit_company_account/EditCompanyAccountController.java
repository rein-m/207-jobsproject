package interface_adapter.edit_company_account;

import use_case.edit_company_account.EditCompanyAccountInputBoundary;
import use_case.edit_company_account.EditCompanyAccountInputData;

public class EditCompanyAccountController {
    private final EditCompanyAccountInputBoundary editCompanyAccountUseCaseInteractor;

    public EditCompanyAccountController(EditCompanyAccountInputBoundary editCompanyAccountUseCaseInteractor) {
        this.editCompanyAccountUseCaseInteractor = editCompanyAccountUseCaseInteractor;
    }

    public void execute(String identifier, String name, String website, String email, String number, String location) {
        final EditCompanyAccountInputData editCompanyAccountInputData = new EditCompanyAccountInputData(identifier, name, website, email, number, location);
        editCompanyAccountUseCaseInteractor.execute(editCompanyAccountInputData);
    }
}
