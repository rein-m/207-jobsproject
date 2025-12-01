package app;

//import data_access.CompanyDataAccessObject;
import entity.CompanyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.company_account.CompanyAccountViewModel;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;
import interface_adapter.edit_company_account.EditCompanyAccountController;
import interface_adapter.edit_company_account.EditCompanyAccountPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.post_job.PostJobController;
import interface_adapter.post_job.PostJobPresenter;
import interface_adapter.post_job.PostJobViewModel;
import use_case.edit_company_account.EditCompanyAccountInputBoundary;
import use_case.edit_company_account.EditCompanyAccountInteractor;
import use_case.edit_company_account.EditCompanyAccountOutputBoundary;
//import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.post_job.PostJobInputBoundary;
import use_case.post_job.PostJobInteractor;
import use_case.post_job.PostJobOutputBoundary;
import view.*;
import view.company_loggedin.CompanyLoggedInView;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardLayout, cardPanel, viewManagerModel);

    final CompanyFactory companyFactory = new CompanyFactory();
//    private CompanyDataAccessObject companyDataAccessObject = new CompanyDataAccessObject(/Users/ethan/IdeaProjects/207-jobsproject/users.csv, companyFactory);


    private LandingView landingView;
    private CompanyLoginSignupView companyLoginSignupView;
    private UserLoginSignupView userLoginSignupView;
    private final LoginViewModel loginViewModel =  new LoginViewModel();


    private CompanyLoggedInView companyLoggedInView;
    private final CompanyLoggedInViewModel companyLoggedInViewModel = new CompanyLoggedInViewModel();
    private CompanyAccountView companyAccountView;
    private final CompanyAccountViewModel companyAccountViewModel  = new CompanyAccountViewModel();
    private PostJobView postJobView;
    private final PostJobViewModel postJobViewModel = new PostJobViewModel();


    public AppBuilder() { cardPanel.setLayout(cardLayout); }

    public AppBuilder addLandingView() {
        landingView = new LandingView(viewManagerModel);
        return this;
    }

    public AppBuilder addCompanyLoginSignupView() {
        companyLoginSignupView = new CompanyLoginSignupView(loginViewModel);
        return this;
    }

//    public AppBuilder addUserLogInUseCase() {
//        LoginOutputBoundary loginOutputBoundary = new LoginInteractor(companyDataAccessObject);
//        return this;
//    }

    public AppBuilder addUserSignupUseCase() {
        return this;
    }

    public AppBuilder addCompanyLoggedInView() {
        companyLoggedInView = new CompanyLoggedInView(companyLoggedInViewModel);
        companyLoggedInView.setPostJobViewModel(postJobViewModel);
        companyLoggedInView.setCompanyAccountViewModel(companyAccountViewModel);
        companyLoggedInView.setViewManagerModel(viewManagerModel);
        cardPanel.add(companyLoggedInView, companyLoggedInView.getViewName());
        return this;
    }

    public AppBuilder addCompanyAccountView() {
        companyAccountView = new CompanyAccountView(companyAccountViewModel);
        companyAccountView.setCompanyLoggedInViewModel(companyLoggedInViewModel);
        companyAccountView.setViewManagerModel(viewManagerModel);
        cardPanel.add(companyAccountView, companyAccountView.getViewName());
        return this;
    }

    public AppBuilder addPostJobView() {
        postJobView = new PostJobView(postJobViewModel);
        cardPanel.add(postJobView, postJobView.getViewName());
        return this;
    }

    public AppBuilder addPostJobUseCase() {
        final PostJobOutputBoundary postJobOutputBoundary =
                new PostJobPresenter(companyLoggedInViewModel, viewManagerModel);
        final PostJobInputBoundary postJobInputBoundary = new PostJobInteractor(postJobOutputBoundary);
        PostJobController postJobController = new PostJobController(postJobInputBoundary);
        postJobView.setPostJobController(postJobController);
        return this;
    }

    public AppBuilder addEditCompanyAccountUseCase() {
        final EditCompanyAccountOutputBoundary editCompanyAccountOutputBoundary =
                new EditCompanyAccountPresenter(companyAccountViewModel);
        final EditCompanyAccountInputBoundary editCompanyAccountInputBoundary =
                new EditCompanyAccountInteractor(editCompanyAccountOutputBoundary);
        EditCompanyAccountController editCompanyAccountController =
                new EditCompanyAccountController(editCompanyAccountInputBoundary);
        companyAccountView.setEditCompanyAccountController(editCompanyAccountController);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame();
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(companyLoggedInView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }
}
