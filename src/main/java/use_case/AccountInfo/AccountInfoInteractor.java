package use_case.AccountInfo;
import entity.User;
import interface_adapter.ResumeShit.resumeUI.ResumeUIPresenter;
import interface_adapter.userAccountInfo.AccountInfoPresenter;

import java.util.ArrayList;
import java.sql.SQLException;

public class AccountInfoInteractor {
    private final AccountInfoPresenter accountInfoPresenter;
    private final AccountInfoDataAccessInterface accountInfoDataAccessInterface;

    public AccountInfoInteractor(AccountInfoPresenter resumeUIOutputBoundary,
                                 AccountInfoDataAccessInterface accountInfoDataAccessInterface) {
        this.accountInfoPresenter = resumeUIOutputBoundary;
        this.accountInfoDataAccessInterface = accountInfoDataAccessInterface;
    }

    public void execute(String userIdentifier) {

        User user = accountInfoDataAccessInterface.get(userIdentifier);
        ArrayList<String> resumes = user.getResumes();
        final AccountInfoOutputData accountInfoOutputData = new AccountInfoOutputData(user.getLocation(),
                user.getEmail(), user.getPhone(), resumes, user.getIdentifier()); // will add resumes later
        accountInfoPresenter.prepareResumeUIView(accountInfoOutputData);
    }
}
