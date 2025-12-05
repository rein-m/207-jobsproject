package interface_adapter.account;

/**
 * Stores the account data for the View layer.
 */
public class AccountState {

    private String identifier;

    private String email;
    private String phone;
    private String address;

    private String education;
    private String workExperience;
    private String projects;
    private String skills;
    private String programmingLanguages;
    private String frameworksAndLibraries;
    private String toolsAndTechnologies;

    private String error;

    public String getIdentifier()              { return identifier; }
    public void   setIdentifier(String id)     { this.identifier = id; }

    public String getEmail()                   { return email; }
    public void   setEmail(String email)       { this.email = email; }

    public String getPhone()                   { return phone; }
    public void   setPhone(String phone)       { this.phone = phone; }

    public String getAddress()                 { return address; }
    public void   setAddress(String address)   { this.address = address; }

    public String getEducation()               { return education; }
    public void   setEducation(String education) { this.education = education; }

    public String getWorkExperience()          { return workExperience; }
    public void   setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getProjects()                { return projects; }
    public void   setProjects(String projects) { this.projects = projects; }

    public String getSkills()                  { return skills; }
    public void   setSkills(String skills)     { this.skills = skills; }

    public String getProgrammingLanguages()    { return programmingLanguages; }
    public void   setProgrammingLanguages(String programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public String getFrameworksAndLibraries()  { return frameworksAndLibraries; }
    public void   setFrameworksAndLibraries(String frameworksAndLibraries) {
        this.frameworksAndLibraries = frameworksAndLibraries;
    }

    public String getToolsAndTechnologies()    { return toolsAndTechnologies; }
    public void   setToolsAndTechnologies(String toolsAndTechnologies) {
        this.toolsAndTechnologies = toolsAndTechnologies;
    }

    public String getError()                   { return error; }
    public void   setError(String error)       { this.error = error; }
}
