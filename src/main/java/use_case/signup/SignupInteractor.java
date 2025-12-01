package use_case.signup;

import entity.Entity;
import entity.UserFactory;
import entity.CompanyFactory;

public class SignupInteractor implements SignupInputBoundary {

    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupCompanyDataAccessInterface companyDataAccessObject;
    private final SignupOutputBoundary signupPresenter;
    private final UserFactory userFactory;
    private final CompanyFactory companyFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject,
                            SignupCompanyDataAccessInterface companyDataAccessObject,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory,
                            CompanyFactory companyFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.companyDataAccessObject = companyDataAccessObject;
        this.signupPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.companyFactory = companyFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        final String identifier = signupInputData.getIdentifier();
        final String entityType = signupInputData.getEntityType();

        if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            signupPresenter.prepareFailView("Passwords don't match.");
            return;
        }

        if (entityType.equalsIgnoreCase("User")) {
            if (userDataAccessObject.existsByIdentifier(identifier)) {
                signupPresenter.prepareFailView("User with identifier " + identifier + " already exists.");
                return;
            }

            Entity newEntity = userFactory.createEntity("", identifier, signupInputData.getPassword(), "", "", "");
            userDataAccessObject.save(newEntity);

            final SignupOutputData signupOutputData = new SignupOutputData(newEntity.getIdentifier(), false);
            signupPresenter.prepareSuccessView(signupOutputData);

        } else if (entityType.equalsIgnoreCase("Company")) {
            if (companyDataAccessObject.existsByIdentifier(identifier)) {
                signupPresenter.prepareFailView("Company with identifier " + identifier + " already exists.");
                return;
            }

            Entity newEntity = companyFactory.create(identifier, signupInputData.getPassword());
            companyDataAccessObject.save(newEntity);

            final SignupOutputData signupOutputData = new SignupOutputData(newEntity.getIdentifier(), false);
            signupPresenter.prepareSuccessView(signupOutputData);

        } else {
            signupPresenter.prepareFailView("Invalid entity type selected.");
        }
    }

    @Override
    public void switchToLoginView() {
        signupPresenter.switchToLoginView();
    }
}