package interface_adapter.userAccountInfo;

import interface_adapter.ResumeShit.resumeUI.ResumeUIViewModel;
import interface_adapter.ViewManagerModel;
import use_case.AccountInfo.AccountInfoOutputData;

public class AccountInfoPresenter {
    private final ResumeUIViewModel resumeUIViewModel;
    private final AccountInforViewModel accountinfoViewModel;
    private final ViewManagerModel viewManagerModel;

    public AccountInfoPresenter(ViewManagerModel viewManagerModel,
                             ResumeUIViewModel resumeUIViewModel,
                             AccountInforViewModel accountinfoViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.resumeUIViewModel = resumeUIViewModel;
        this.accountinfoViewModel = accountinfoViewModel;
    }

    public void prepareResumeUIView(AccountInfoOutputData response) {

        final AccountInfoState accountInfoState = accountinfoViewModel.getState();
        accountInfoState.setLocation(response.getLocation());
        accountInfoState.setEmail(response.getEmail());
        accountInfoState.setPhone(response.getPhone());
        accountInfoState.setResumes(response.getResumes());
        accountInfoState.setUserIdentifier(response.getIdentifier());


        this.accountinfoViewModel.setState(accountInfoState);
        this.accountinfoViewModel.firePropertyChanged();

        this.viewManagerModel.setState(accountinfoViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
