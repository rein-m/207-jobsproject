package interface_adapter.company_account;

import interface_adapter.ViewModel;

public class CompanyAccountViewModel extends ViewModel<CompanyAccountState> {

    public CompanyAccountViewModel() {
        super("company account");
        this.setState(new CompanyAccountState());
    }
}
