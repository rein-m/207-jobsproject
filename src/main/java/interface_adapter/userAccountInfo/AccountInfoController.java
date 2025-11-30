package interface_adapter.userAccountInfo;
import entity.User;
import use_case.AccountInfo.AccountInfoInputBoundary;
import use_case.AccountInfo.AccountInfoInteractor;
import use_case.ResumeShit.ResumeUI.ResumeUIInteractor;


public class AccountInfoController {
    private final AccountInfoInteractor accountInfoInteractor;

    public AccountInfoController(AccountInfoInteractor resumeUIInteractor) {
        this.accountInfoInteractor = resumeUIInteractor;
    }

    public void execute(User user) {
        accountInfoInteractor.execute(user);
    }

}
