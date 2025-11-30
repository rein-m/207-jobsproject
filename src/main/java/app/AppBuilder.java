package app;

import entity.*;

import interface_adapter.*;
import interface_adapter.ResumeShit.resumeUI.ResumeUIControler;
import interface_adapter.ResumeShit.resumeUI.ResumeUIPresenter;
import interface_adapter.ResumeShit.resumeUI.ResumeUIViewModel;
import interface_adapter.userAccountInfo.AccountInfoController;
import interface_adapter.userAccountInfo.AccountInfoPresenter;
import interface_adapter.userAccountInfo.AccountInforViewModel;
//import use_case.*;
import use_case.AccountInfo.AccountInfoInteractor;
import use_case.ResumeShit.ResumeUI.ResumeUIInteractor;
import view.*;

import java.awt.*;
import javax.swing.*;


public class AppBuilder {
    private final JPanel cardpanel =  new JPanel();
    private final CardLayout cardlayout = new CardLayout();
    final UserFactory userFactory = new UserFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardpanel, cardlayout, viewManagerModel);

    private UserAccountInfoView userAccountInfoView;
    private AccountInforViewModel  accountInforViewModel;
    private ResumeView  resumeView;
    private ResumeUIViewModel resumeUIViewModel;

    public AppBuilder() {
        cardpanel.setLayout(cardlayout);}

    public AppBuilder addUserAccountInfoView() {
        accountInforViewModel = new AccountInforViewModel();
        userAccountInfoView = new UserAccountInfoView(accountInforViewModel);
        cardpanel.add(userAccountInfoView, userAccountInfoView.getViewName());
        return this;
    }

    public AppBuilder addResumeView() {
        resumeUIViewModel = new ResumeUIViewModel();
        resumeView = new ResumeView(resumeUIViewModel);
        cardpanel.add(resumeView, resumeUIViewModel.getViewName());
        return this;
    }

    public AppBuilder addResumeUIUseCase() {
        final ResumeUIPresenter resumeUIPresenter = new ResumeUIPresenter(viewManagerModel, resumeUIViewModel,
                accountInforViewModel);
        final ResumeUIInteractor resumeUIInteractor = new ResumeUIInteractor(resumeUIPresenter);
        ResumeUIControler resumeUIControler = new ResumeUIControler(resumeUIInteractor);
        userAccountInfoView.setAccountInfoControler(resumeUIControler);
        return this;
    }

    public AppBuilder addAccountInfoUseCase() {
        final AccountInfoPresenter resumeUIPresenter = new AccountInfoPresenter(viewManagerModel, resumeUIViewModel,
                accountInforViewModel);
        final AccountInfoInteractor resumeUIInteractor = new AccountInfoInteractor(resumeUIPresenter);
        AccountInfoController resumeUIControler = new AccountInfoController(resumeUIInteractor);
        resumeView.setResumeUIControler(resumeUIControler);
        return this;
    }


    public JFrame build() {
        final JFrame application = new JFrame("User Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardpanel);

        viewManagerModel.setState(userAccountInfoView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

}
