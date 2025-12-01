package use_case.ResumeShit.addResume;

public class AddResumeInputData {
    String filepath;
    String userIdentifier;


    public AddResumeInputData(String filepath, String userIdentifier) {
        this.filepath = filepath;
        this.userIdentifier = userIdentifier;
    }
    public String getFilepath() {
        return filepath;
    }
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
