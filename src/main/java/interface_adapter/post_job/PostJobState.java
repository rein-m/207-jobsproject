package interface_adapter.post_job;

public class PostJobState {
    private String jobTitle;
    private String jobDescription;

    public PostJobState() {}

    public String getJobTitle() { return this.jobTitle; }

    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getJobDescription() { return this.jobDescription; }

    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }
}
