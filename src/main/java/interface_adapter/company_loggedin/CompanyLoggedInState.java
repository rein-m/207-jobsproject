package interface_adapter.company_loggedin;

import java.util.HashMap;

public class CompanyLoggedInState {
    private String companyName;
    private  HashMap<String, String> jobListings = new HashMap<>();


    public CompanyLoggedInState() {}

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyName() { return this.companyName; }

    public void addJobListing(String jobTitle, String jobDescription) {
        this.jobListings.put(jobTitle, jobDescription);
    }
}
