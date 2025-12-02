package use_case.edit_company_account;

public class EditCompanyAccountInputData {
    private String identifier;
    private String name;
    private String website;
    private String email;
    private String number;
    private String location;

    public EditCompanyAccountInputData(String identifier, String name, String website, String email, String number, String location) {
        this.identifier = identifier;
        this.name = name;
        this.website = website;
        this.email = email;
        this.number = number;
        this.location = location;
    }

    public String getIdentifier() { return identifier; }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
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
}
