package interface_adapter.ResumeShit.resumeUI;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.userAccountInfo.AccountInfoState;
import interface_adapter.userAccountInfo.AccountInforViewModel;

public class ResumeUIPresenter {

    private final AccountInforViewModel accountinfoViewModel;
    private final ResumeUIViewModel resumeUIViewModel;
    private final ViewManagerModel viewManagerModel;

    public ResumeUIPresenter(ViewManagerModel viewManagerModel,
                             ResumeUIViewModel resumeUIViewModel,
                             AccountInforViewModel accountinfoViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.resumeUIViewModel = resumeUIViewModel;
        this.accountinfoViewModel = accountinfoViewModel;
    }

    public void prepareResumeUIView(String userIdentifier) {
        final ResumeUIState resumeUIState = resumeUIViewModel.getState();
        resumeUIState.SetUserIdentifier(userIdentifier);

        this.resumeUIViewModel.setState(resumeUIState);
        this.resumeUIViewModel.firePropertyChanged();

        this.viewManagerModel.setState(resumeUIViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }


}
