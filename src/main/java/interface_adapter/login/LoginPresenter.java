package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedin.LoggedinViewModel;
import interface_adapter.loggedin.LoggedinState;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;
import interface_adapter.company_loggedin.CompanyLoggedInState;

import interface_adapter.loggedin.LoggedinViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    private final LoggedinViewModel userLoggedInViewModel;
    private final CompanyLoggedInViewModel companyLoggedInViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,
                          LoggedinViewModel userLoggedInViewModel,
                          CompanyLoggedInViewModel companyLoggedInViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.userLoggedInViewModel = userLoggedInViewModel;
        this.companyLoggedInViewModel = companyLoggedInViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {

        if ("Company".equals(response.getType())) {

            final CompanyLoggedInState state = companyLoggedInViewModel.getState();
            state.setCompanyName(response.getIdentifier());
            this.companyLoggedInViewModel.setState(state);
            this.companyLoggedInViewModel.firePropertyChanged();

            this.viewManagerModel.setState(companyLoggedInViewModel.getViewName());

        } else {

            final LoggedinState state = userLoggedInViewModel.getState();
            state.setIdentifier(response.getIdentifier());
            this.userLoggedInViewModel.setState(state);
            this.userLoggedInViewModel.firePropertyChanged();

            this.viewManagerModel.setState(userLoggedInViewModel.getViewName());
        }

        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}