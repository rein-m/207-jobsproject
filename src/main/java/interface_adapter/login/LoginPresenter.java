package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.company_loggedin.CompanyLoggedInState;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary{

    private final LoginViewModel loginViewModel;
    private final CompanyLoggedInViewModel companyLoggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, CompanyLoggedInViewModel companyLoggedInViewModel,
                          LoginViewModel loginViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.companyLoggedInViewModel = companyLoggedInViewModel;

    }

    @Override
    public void prepareSuccessView(LoginOutputData response){
        //send to 2 different states
        final CompanyLoggedInState companyLoggedInState = companyLoggedInViewModel.getState();
        companyLoggedInState.setCompanyName(response.getIdentifier());
        this.companyLoggedInViewModel.setState(companyLoggedInState);
        this.companyLoggedInViewModel.firePropertyChange();

        this.viewManagerModel.setState(companyLoggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }
    @Override
    public void prepareFailView(String error){
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChange();
    }
}
