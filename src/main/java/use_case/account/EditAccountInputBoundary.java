package use_case.account;

/**
 * Input boundary for the Edit Account use case.
 * Implemented only by the interactor.
 */
public interface EditAccountInputBoundary {

    void execute(EditAccountInputData inputData);
}
