package use_case.edit_company_account;

import entity.Company;

public interface EditCompanyAccountDataAccessInterface {
    public Company getCompany(String identifier);
    public void saveData();
}
