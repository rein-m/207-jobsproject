package interface_adapter.company_loggedin;

import interface_adapter.ViewModel;

public class CompanyLoggedInViewModel extends ViewModel<CompanyLoggedInState> {

    public CompanyLoggedInViewModel() {
        super("company logged in");
        this.setState(new CompanyLoggedInState());
    }
}
