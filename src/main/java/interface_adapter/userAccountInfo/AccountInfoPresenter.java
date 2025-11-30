package interface_adapter.userAccountInfo;

import interface_adapter.ResumeShit.resumeUI.ResumeUIViewModel;
import interface_adapter.ViewManagerModel;

public class AccountInfoPresenter {
    private final ResumeUIViewModel resumeUIViewModel;
    private final AccountInforViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public AccountInfoPresenter(ViewManagerModel viewManagerModel,
                             ResumeUIViewModel resumeUIViewModel,
                             AccountInforViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.resumeUIViewModel = resumeUIViewModel;
        this.loginViewModel = loginViewModel;
    }

    public void prepareResumeUIView() {

        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
