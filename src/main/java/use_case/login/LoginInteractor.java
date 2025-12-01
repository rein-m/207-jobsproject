//package use_case.login;
//import entity.Entity;
//import entity.GenericFactory;
//
//
//public class LoginInteractor implements LoginInputBoundary {
//    private final LoginUserDataAccessInterface userDataAccessObject;
//    private final LoginOutputBoundary loginPresenter;
//
//    public LoginInteractor(LoginUserDataAccessInterface userDataAccessObject, LoginOutputBoundary loginPresenter) {
//        this.userDataAccessObject = userDataAccessObject;
//        this.loginPresenter = loginPresenter;
//    }
//
//    @Override
//    public void execute(LoginInputData loginInputData) {
//        final String identifier = loginInputData.getIdentifier();
//        final String password = loginInputData.getPassword();
//        final String entityType = loginInputData.getEntityType();
//
//        if(!userDataAccessObject.existsByIdentifier(identifier)){
//            loginPresenter.prepareFailView(identifier + " ("+ entityType+"): Account does not exist");
//            return;
//        }
//        final Entity loggedInEntity = userDataAccessObject.get(identifier, entityType);
//
//        if(!password.equals(loggedInEntity.getPassword())){
//            loginPresenter.prepareFailView("Incorrect password for " + identifier);
//            return;
//        }
//
//        final LoginOutputData loginOutputData = new LoginOutputData(loggedInEntity.getIdentifier(), false);
//        loginPresenter.prepareSuccessView(loginOutputData);
//    }
//}
