package use_case.login;

import java.util.ArrayList;

public class LoginOutputData {
    private final String identifier;
    private final String type;
    private final boolean useCaseFailed;
    private ArrayList<ArrayList<String>> jobs;

    public LoginOutputData(String identifier, String type, boolean useCaseFailed) {
        this.identifier = identifier;
        this.type = type;
        this.useCaseFailed = useCaseFailed;
    }

    public LoginOutputData(String identifier, String type, boolean useCaseFailed, ArrayList<ArrayList<String>> jobs) {
        this.identifier = identifier;
        this.type = type;
        this.useCaseFailed = useCaseFailed;
        initjobs(jobs);
    }
    public void initjobs(ArrayList<ArrayList<String>> jobs) {
        this.jobs = jobs;
    }
    public String getIdentifier() {
        return identifier;
    }
    public String getType() {
        return type;
    }

    public ArrayList<ArrayList<String>> getJobs() {
        return jobs;
    }
}
