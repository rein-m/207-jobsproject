package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {
    private final SignupInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignupInputBoundary entitySignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = entitySignupUseCaseInteractor;
    }

    public void execute(String type, String identifier, String password1, String password2){
        final SignupInputData signupInputData = new SignupInputData(
                type, identifier, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);

    }

    public void switchtoLoginView(){
        userSignupUseCaseInteractor.switchToLoginView();
    }
}
