package interface_adapter.userAccountInfo;
import use_case.AccountInfo.AccountInfoInteractor;
import use_case.ResumeShit.ResumeUI.ResumeUIInteractor;


public class AccountInfoController {
    private final AccountInfoInteractor accountInfoInteractor;

    public AccountInfoController(AccountInfoInteractor resumeUIInteractor) {
        this.accountInfoInteractor = resumeUIInteractor;
    }

    public void execute() {
        accountInfoInteractor.execute();
    }

}
