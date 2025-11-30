package use_case.AccountInfo;
import interface_adapter.ResumeShit.resumeUI.ResumeUIPresenter;
import interface_adapter.userAccountInfo.AccountInfoPresenter;

public class AccountInfoInteractor {
    private final AccountInfoPresenter accountInfoPresenter;

    public AccountInfoInteractor(AccountInfoPresenter resumeUIOutputBoundary) {
        this.accountInfoPresenter = resumeUIOutputBoundary;
    }

    public void execute(){
        accountInfoPresenter.prepareResumeUIView();
    }
}
