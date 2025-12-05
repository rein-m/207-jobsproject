package use_case.account;

import entity.User;

/**
 * Use Case interactor for editing a user's account info and qualifications.
 */
public class EditAccountInteractor implements EditAccountInputBoundary {

    private final EditAccountUserDataAccessInterface userDataAccess;
    private final EditAccountOutputBoundary presenter;

    public EditAccountInteractor(EditAccountUserDataAccessInterface userDataAccess,
                                 EditAccountOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(EditAccountInputData inputData) {

        String id = inputData.getIdentifier();

        if (!userDataAccess.existsByIdentifier(id)) {
            presenter.prepareFailView("User '" + id + "' does not exist.");
            return;
        }

        User user = userDataAccess.get(id);

        // Update entity with new values.
        user.updateContactInfo(
                inputData.getAddress(),
                inputData.getEmail(),
                inputData.getPhone()
        );

        user.updateQualifications(
                inputData.getEducation(),
                inputData.getWorkExperience(),
                inputData.getProjects(),
                inputData.getSkills(),
                inputData.getProgrammingLanguages(),
                inputData.getFrameworksAndLibraries(),
                inputData.getToolsAndTechnologies()
        );

        // Persist the changes
        userDataAccess.save(user);

        // Build output data from the entity
        EditAccountOutputData outputData = new EditAccountOutputData(
                id,
                user.getEmail(),
                user.getPhone(),
                user.getLocation(),               // address
                user.getEducation(),
                user.getWorkExperience(),
                user.getProjects(),
                user.getSkills(),
                user.getProgrammingLanguages(),
                user.getFrameworksAndLibraries(),
                user.getToolsAndTechnologies()
        );

        presenter.prepareSuccessView(outputData);
    }
}
