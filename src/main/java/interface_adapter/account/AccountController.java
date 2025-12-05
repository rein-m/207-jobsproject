package interface_adapter.account;

import use_case.account.EditAccountInputBoundary;
import use_case.account.EditAccountInputData;

/**
 * Controller for the Edit Account use case.
 * Called by the Swing views.
 */
public class AccountController {

    private final EditAccountInputBoundary interactor;

    public AccountController(EditAccountInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void editAccount(
            String identifier,
            String email,
            String phone,
            String address,
            String education,
            String workExperience,
            String projects,
            String skills,
            String programmingLanguages,
            String frameworksAndLibraries,
            String toolsAndTechnologies
    ) {
        EditAccountInputData inputData = new EditAccountInputData(
                identifier,
                email,
                phone,
                address,
                education,
                workExperience,
                projects,
                skills,
                programmingLanguages,
                frameworksAndLibraries,
                toolsAndTechnologies
        );
        interactor.execute(inputData);
    }
}
