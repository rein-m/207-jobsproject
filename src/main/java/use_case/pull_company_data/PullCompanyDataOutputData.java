package use_case.pull_company_data;

public class PullCompanyDataOutputData {
    private String companyName;
    private String email;
    private String number;
    private String location;
    private String website;

    public PullCompanyDataOutputData(String companyName, String email, String number, String location, String website) {
        this.companyName = companyName;
        this.email = email;
        this.number = number;
        this.location = location;
        this.website = website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }
}
