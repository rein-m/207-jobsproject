package use_case.account;

/**
 * Output boundary for the Edit Account use case.
 * Implemented by the Presenter.
 */
public interface EditAccountOutputBoundary {

    void prepareSuccessView(EditAccountOutputData outputData);

    void prepareFailView(String errorMessage);
}
