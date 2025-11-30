package interface_adapter.ResumeShit.resumeUI;

import interface_adapter.ViewManagerModel;
import interface_adapter.userAccountInfo.AccountInforViewModel;

public class ResumeUIPresenter {

    private final AccountInforViewModel loginViewModel;
    private final ResumeUIViewModel resumeUIViewModel;
    private final ViewManagerModel viewManagerModel;

    public ResumeUIPresenter(ViewManagerModel viewManagerModel,
                             ResumeUIViewModel resumeUIViewModel,
                             AccountInforViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.resumeUIViewModel = resumeUIViewModel;
        this.loginViewModel = loginViewModel;
    }

    public void prepareResumeUIView() {
        // On success, switch to the logged in view.

//        this.loggedInViewModel.setState();
//        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setState(resumeUIViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }


}
