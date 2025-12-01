package interface_adapter.login;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginViewModel;

public class LoginViewModel extends ViewModel<LoginState> {
    public LoginViewModel() {
        super("log in");
        setState(new LoginState());
    }

}