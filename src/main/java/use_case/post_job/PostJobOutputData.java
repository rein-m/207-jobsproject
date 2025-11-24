package use_case.post_job;

public class PostJobOutputData {
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;

    public PostJobOutputData(String jobTitle, String jobDescription, String jobLocation) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
    }
    public String getJobTitle() { return this.jobTitle; }

    public String getJobDescription() { return this.jobDescription; }

    public String getJobLocation() { return this.jobLocation; }
}
