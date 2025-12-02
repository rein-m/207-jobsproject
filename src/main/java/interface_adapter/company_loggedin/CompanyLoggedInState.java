package interface_adapter.company_loggedin;

import view.company_loggedin.JobData;

import java.util.ArrayList;
import java.util.List;

public class CompanyLoggedInState {
    private String identifier = "comp002";
    private String companyName;
    private  ArrayList<ArrayList<String>> jobListings = new ArrayList<>();


    public CompanyLoggedInState() {}

    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {}

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyName() { return this.companyName; }

    public void setJobListings(ArrayList<ArrayList<String>> jobListings) { this.jobListings = jobListings; }

    public ArrayList<ArrayList<String>> getJobListings() { return this.jobListings; }

    public void addJobListing(String jobTitle, String jobDescription,  String jobLocation) {
        ArrayList<String> jobListing = new ArrayList<>();
        jobListing.add(jobTitle);
        jobListing.add(jobDescription);
        jobListing.add(jobLocation);
        this.jobListings.add(jobListing);
    }
}
