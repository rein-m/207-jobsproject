package use_case.ResumeShit.addResume;

import interface_adapter.ResumeShit.addResume.AddResumePresenter;
import interface_adapter.userAccountInfo.AccountInfoPresenter;
import use_case.AccountInfo.AccountInfoDataAccessInterface;

public class AddResumeInteractor {
    private final AddResumePresenter accountInfoPresenter;
    private final AddResumeDataAccessInterface accountInfoDataAccessInterface;

    public AddResumeInteractor(
            AddResumePresenter accountInfoPresenter,
            AddResumeDataAccessInterface accountInfoDataAccessInterface) {
        this.accountInfoPresenter = accountInfoPresenter;
        this.accountInfoDataAccessInterface = accountInfoDataAccessInterface;
    }

    public void execute(AddResumeInputData addResumeInputData) {

    }
}
