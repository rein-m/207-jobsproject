package interface_adapter.login;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor){
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public void execute(String Entity, String username, String password){
        final LoginInputData loginInputData = new LoginInputData(Entity, username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

}
