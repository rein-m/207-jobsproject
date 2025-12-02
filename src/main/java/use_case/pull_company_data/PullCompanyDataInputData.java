package use_case.pull_company_data;

public class PullCompanyDataInputData {
    private String identifier;

    public PullCompanyDataInputData(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() { return identifier; }
}
