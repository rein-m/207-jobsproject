package interface_adapter.company_loggedin;

import java.util.ArrayList;
import java.util.List;

public class CompanyLoggedInState {
    private String companyName;
    private  List<List<String>> jobListings = new ArrayList<>();


    public CompanyLoggedInState() {}

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyName() { return this.companyName; }

    public void setJobListings(List<List<String>> jobListings) { this.jobListings = jobListings; }

    public List<List<String>> getJobListings() { return this.jobListings; }

    public void addJobListing(String jobTitle, String jobDescription,  String jobLocation) {
        List<String> jobListing = new ArrayList<>();
        jobListing.add(jobTitle);
        jobListing.add(jobDescription);
        jobListing.add(jobLocation);
        this.jobListings.add(jobListing);
    }
}
