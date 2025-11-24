package use_case.signup;

import entity.Entity;
import entity.GenericFactory;
import entity.UserFactory;
import entity.CompanyFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {

    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary signupPresenter;

    private final UserFactory userFactory;
    private final CompanyFactory companyFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory,
                            CompanyFactory companyFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.signupPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.companyFactory = companyFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {

        final String identifier = signupInputData.getIdentifier();
        final String entityType = signupInputData.getEntityType();
        if (userDataAccessObject.existsByIdentifier(identifier)) {
            signupPresenter.prepareFailView(entityType + " with identifier " + identifier + " already exists.");
            return;
        }

        if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            signupPresenter.prepareFailView("Passwords don't match.");
            return;
        }

        final Entity newEntity;

        if (entityType.equalsIgnoreCase("User")) {

            // this function takes 5 parameters name, password, location, email, phone, but only passing it with 2.
            newEntity = userFactory.createEntity(identifier, signupInputData.getPassword());

        } else if (entityType.equalsIgnoreCase("Company")) {


            newEntity = companyFactory.create(identifier, signupInputData.getPassword());

        } else {
            signupPresenter.prepareFailView("Invalid entity type selected.");
            return;
        }

        userDataAccessObject.save(newEntity);

        final SignupOutputData signupOutputData = new SignupOutputData(newEntity.getIdentifier(), false);
        signupPresenter.prepareSuccessView(signupOutputData);
    }

    @Override
    public void switchToLoginView() {
        signupPresenter.switchToLoginView();
    }
}