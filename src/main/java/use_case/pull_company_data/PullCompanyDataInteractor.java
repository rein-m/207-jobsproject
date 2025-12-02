package use_case.pull_company_data;

import entity.Company;

public class PullCompanyDataInteractor implements PullCompanyDataInputBoundary {
    private final PullCompanyDataOutputBoundary pullCompanyDataPresenter;
    private final PullCompanyDataUserDataAccessInterface pullCompanyDataUserDataAccessInterface;

    public PullCompanyDataInteractor(PullCompanyDataOutputBoundary pullCompanyDataPresenter,
                                     PullCompanyDataUserDataAccessInterface pullCompanyDataUserDataAccessInterface) {
        this.pullCompanyDataPresenter = pullCompanyDataPresenter;
        this.pullCompanyDataUserDataAccessInterface = pullCompanyDataUserDataAccessInterface;
    }

    public void execute(PullCompanyDataInputData pullCompanyDataInputData) {
        Company company = pullCompanyDataUserDataAccessInterface.getCompany(pullCompanyDataInputData.getIdentifier());
        String name = company.getCompanyName();
        String email = company.getEmail();
        String location = company.getLocation();
        String number = company.getNumber();
        String website  = company.getWebsite();
        final PullCompanyDataOutputData pullCompanyDataOutputData =
                new PullCompanyDataOutputData(name, email, location, number, website);
        pullCompanyDataPresenter.updateCompanyAccountState(pullCompanyDataOutputData);
    }

}
