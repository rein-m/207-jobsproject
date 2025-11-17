package use_case.post_job;

public class PostJobOutputData {
    private String jobTitle;
    private String jobDescription;

    public PostJobOutputData(String jobTitle, String jobDescription) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
    }
    public String getJobTitle() { return this.jobTitle; }

    public String getJobDescription() { return this.jobDescription; }
}
