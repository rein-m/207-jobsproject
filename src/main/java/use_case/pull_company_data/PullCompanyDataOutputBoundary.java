package use_case.pull_company_data;

import entity.Company;

public interface PullCompanyDataOutputBoundary {
    void updateCompanyAccountState(PullCompanyDataOutputData pullCompanyDataOutputData);
}
