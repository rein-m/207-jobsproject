package use_case.pull_company_data;

import entity.Company;

public interface PullCompanyDataUserDataAccessInterface {
    public Company getCompany(String name);

    void saveData();
}
