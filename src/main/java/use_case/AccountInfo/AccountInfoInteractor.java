package use_case.AccountInfo;
import entity.User;
import interface_adapter.ResumeShit.resumeUI.ResumeUIPresenter;
import interface_adapter.userAccountInfo.AccountInfoPresenter;

public class AccountInfoInteractor {
    private final AccountInfoPresenter accountInfoPresenter;
    private final AccountInfoDataAccessInterface accountInfoDataAccessInterface;

    public AccountInfoInteractor(AccountInfoPresenter resumeUIOutputBoundary,
                                 AccountInfoDataAccessInterface accountInfoDataAccessInterface) {
        this.accountInfoPresenter = resumeUIOutputBoundary;
        this.accountInfoDataAccessInterface = accountInfoDataAccessInterface;
    }

    public void execute(User user) {

        final AccountInfoOutputData accountInfoOutputData = new AccountInfoOutputData(user);
        accountInfoPresenter.prepareResumeUIView(accountInfoOutputData);
    }
}
