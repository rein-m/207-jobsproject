package use_case.login;

import entity.Entity;

public class LoginInteractor implements LoginInputBoundary {

    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginCompanyDataAccessInterface companyDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessObject,
                           LoginCompanyDataAccessInterface companyDataAccessObject,
                           LoginOutputBoundary loginPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.companyDataAccessObject = companyDataAccessObject;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String identifier = loginInputData.getIdentifier();
        final String password = loginInputData.getPassword();
        final String type = loginInputData.getEntityType();

        if ("User".equalsIgnoreCase(type)) {
            if (!userDataAccessObject.existsByIdentifier(identifier)) {
                loginPresenter.prepareFailView(identifier + ": Account does not exist.");
            } else {
                String pwd = userDataAccessObject.getPassword(identifier);
                if (!password.equals(pwd)) {
                    loginPresenter.prepareFailView("Incorrect password for " + identifier + ".");
                } else {
                    Entity user = userDataAccessObject.get(identifier);
                    LoginOutputData loginOutputData = new LoginOutputData(user.getIdentifier(), "User", false);
                    loginPresenter.prepareSuccessView(loginOutputData);
                }
            }

        } else if ("Company".equalsIgnoreCase(type)) {
            if (!companyDataAccessObject.existsByIdentifier(identifier)) {
                loginPresenter.prepareFailView(identifier + ": Company account does not exist.");
            } else {
                String pwd = companyDataAccessObject.getPassword(identifier);
                if (!password.equals(pwd)) {
                    loginPresenter.prepareFailView("Incorrect password for company " + identifier + ".");
                } else {
                    Entity company = companyDataAccessObject.get(identifier);
                    LoginOutputData loginOutputData = new LoginOutputData(company.getIdentifier(), "Company", false);
                    loginPresenter.prepareSuccessView(loginOutputData);
                }
            }

        } else {
            loginPresenter.prepareFailView("Unknown account type.");
        }
    }
}