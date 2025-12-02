package interface_adapter.pull_company_data;

import use_case.pull_company_data.PullCompanyDataInputBoundary;
import use_case.pull_company_data.PullCompanyDataInputData;

public class PullCompanyDataController {
    private final PullCompanyDataInputBoundary pullCompanyDataUseCaseInteractor;
    public PullCompanyDataController(PullCompanyDataInputBoundary pullCompanyDataUseCaseInteractor) {
        this.pullCompanyDataUseCaseInteractor = pullCompanyDataUseCaseInteractor;
    }

    public void execute(String identifier) {
        final PullCompanyDataInputData pullCompanyDataInputData = new PullCompanyDataInputData(identifier);
        pullCompanyDataUseCaseInteractor.execute(pullCompanyDataInputData);
    }
}
