package interface_adapter.loggedin;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoggedinController {
    private final LoginInputBoundary loginUseCaseInteractor;

    public LoggedinController(LoginInputBoundary loginUseCaseInteractor){
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public void execute(String Entity, String username, String password){
        final LoginInputData loginInputData = new LoginInputData(Entity, username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

}
