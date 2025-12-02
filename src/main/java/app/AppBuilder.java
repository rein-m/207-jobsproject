package app;

import data_access.CompanyDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.CompanyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.UserFactory;
import entity.CompanyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.company_loggedin.*;
import interface_adapter.loggedin.LoggedinViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.post_job.PostJobViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.login.*;
import use_case.signup.*;
import view.*;
import view.ApplicantLoggedInUI.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    final UserFactory userFactory = new UserFactory();
    final CompanyFactory companyFactory = new CompanyFactory();

    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardLayout, cardPanel, viewManagerModel);


    final UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory);

    final CompanyDataAccessObject companyDataAccessObject = new CompanyDataAccessObject(companyFactory);

    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private Logged userLoggedInViewModel;
    private CompanyLoggedInViewModel companyLoggedInViewModel;

    private LandingView landingView;
    private UserLoginView userLoginView;
    private CompanyLoginView companyLoginView;
    private ApplicantLoggedInView userLoggedInView;
    private CompanyLoggedInView companyLoggedInView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addLandingView() {
        landingView = new LandingView(viewManagerModel);
        cardPanel.add(landingView, landingView.viewName);
        return this;
    }

    public AppBuilder addLoginViews() {
        loginViewModel = new LoginViewModel();

        userLoginView = new UserLoginView(loginViewModel);
        companyLoginView = new CompanyLoginView(loginViewModel);

        cardPanel.add(userLoginView, userLoginView.viewName);
        cardPanel.add(companyLoginView, companyLoginView.viewName);
        return this;
    }

    public AppBuilder addLoggedInViews() {
        userLoggedInViewModel = new LoggedinViewModel();
        companyLoggedInViewModel = new CompanyLoggedInViewModel();

        userLoggedInView = new ApplicantLoggedInView();
        companyLoggedInView = new CompanyLoggedInView(companyLoggedInViewModel, new PostJobViewModel(), viewManagerModel);

        cardPanel.add(userLoggedInView, userLoggedInView.getViewName());
        cardPanel.add(companyLoggedInView, companyLoggedInView.getViewName());
        return this;
    }

    public AppBuilder addSignupUseCase() {
        signupViewModel = new SignupViewModel();

        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);

        final SignupInputBoundary signupInteractor = new SignupInteractor(
                userDataAccessObject,
                (SignupCompanyDataAccessInterface) companyDataAccessObject,
                signupOutputBoundary,
                userFactory,
                companyFactory
        );

        SignupController controller = new SignupController(signupInteractor);

        userLoginView.setSignupController(controller);
        companyLoginView.setSignupController(controller);

        return this;
    }

    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loginViewModel, userLoggedInViewModel, companyLoggedInViewModel);

        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject,
                (LoginCompanyDataAccessInterface) companyDataAccessObject,
                loginOutputBoundary
        );

        LoginController loginController = new LoginController(loginInteractor);

        userLoginView.setLoginController(loginController);
        companyLoginView.setLoginController(loginController);

        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(landingView.viewName);
        viewManagerModel.firePropertyChanged();

        return application;
    }
}