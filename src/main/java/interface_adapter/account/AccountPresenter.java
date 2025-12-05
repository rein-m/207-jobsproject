package interface_adapter.account;

import use_case.account.EditAccountOutputBoundary;
import use_case.account.EditAccountOutputData;

/**
 * Presenter for the Edit Account use case.
 * Converts OutputData into changes in the AccountViewModel.
 */
public class AccountPresenter implements EditAccountOutputBoundary {

    private final AccountViewModel accountViewModel;

    public AccountPresenter(AccountViewModel accountViewModel) {
        this.accountViewModel = accountViewModel;
    }

    @Override
    public void prepareSuccessView(EditAccountOutputData outputData) {
        AccountState state = accountViewModel.getState();

        state.setIdentifier(outputData.getIdentifier());
        state.setEmail(outputData.getEmail());
        state.setPhone(outputData.getPhone());
        state.setAddress(outputData.getAddress());

        state.setEducation(outputData.getEducation());
        state.setWorkExperience(outputData.getWorkExperience());
        state.setProjects(outputData.getProjects());
        state.setSkills(outputData.getSkills());
        state.setProgrammingLanguages(outputData.getProgrammingLanguages());
        state.setFrameworksAndLibraries(outputData.getFrameworksAndLibraries());
        state.setToolsAndTechnologies(outputData.getToolsAndTechnologies());

        state.setError(null);

        accountViewModel.setState(state);
        accountViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        AccountState state = accountViewModel.getState();
        state.setError(errorMessage);
        accountViewModel.setState(state);
        accountViewModel.firePropertyChanged();
    }
}
