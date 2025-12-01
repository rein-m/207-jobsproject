package use_case.ResumeShit.addResume;

import java.util.ArrayList;

public class AddResumeOutputData {
    private final String username;
    private final ArrayList<String> resumes;

    public AddResumeOutputData(String username, ArrayList<String> resumes) {
        this.username = username;
        this.resumes = resumes;
    }

}
