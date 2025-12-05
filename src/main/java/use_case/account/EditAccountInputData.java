package use_case.account;

/**
 * Input data created by the Controller and passed to the Interactor.
 */
public class EditAccountInputData {

    private final String identifier;

    // Basic information
    private final String email;
    private final String phone;
    private final String address;

    // Qualifications
    private final String education;
    private final String workExperience;
    private final String projects;
    private final String skills;
    private final String programmingLanguages;
    private final String frameworksAndLibraries;
    private final String toolsAndTechnologies;

    public EditAccountInputData(
            String identifier,
            String email,
            String phone,
            String address,
            String education,
            String workExperience,
            String projects,
            String skills,
            String programmingLanguages,
            String frameworksAndLibraries,
            String toolsAndTechnologies
    ) {
        this.identifier = identifier;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.workExperience = workExperience;
        this.projects = projects;
        this.skills = skills;
        this.programmingLanguages = programmingLanguages;
        this.frameworksAndLibraries = frameworksAndLibraries;
        this.toolsAndTechnologies = toolsAndTechnologies;
    }

    public String getIdentifier()              { return identifier; }
    public String getEmail()                   { return email; }
    public String getPhone()                   { return phone; }
    public String getAddress()                 { return address; }
    public String getEducation()               { return education; }
    public String getWorkExperience()          { return workExperience; }
    public String getProjects()                { return projects; }
    public String getSkills()                  { return skills; }
    public String getProgrammingLanguages()    { return programmingLanguages; }
    public String getFrameworksAndLibraries()  { return frameworksAndLibraries; }
    public String getToolsAndTechnologies()    { return toolsAndTechnologies; }
}
