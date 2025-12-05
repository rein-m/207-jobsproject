package entity;

/**
 * Entity representing an application user.
 */
public class User {

    // --- identity + authentication ---

    private final String identifier;

    private final String password;

    // --- basic contact info ---

    private String location;

    private String email;
    private String phone;

    // --- qualifications ---

    private String education;
    private String workExperience;
    private String projects;
    private String skills;
    private String programmingLanguages;
    private String frameworksAndLibraries;
    private String toolsAndTechnologies;

    /**
     * Constructor used by existing signup code.
     * Qualifications are initialized to empty strings and can be
     * updated later through updateQualifications or setters.
     */
    public User(String name,
                String password,
                String location,
                String email,
                String phone) {

        this.identifier = name;
        this.password = password;
        this.location = location;
        this.email = email;
        this.phone = phone;

        // Initializes qualifications to empty so they're never null.
        this.education = "";
        this.workExperience = "";
        this.projects = "";
        this.skills = "";
        this.programmingLanguages = "";
        this.frameworksAndLibraries = "";
        this.toolsAndTechnologies = "";
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    // Optional helper if some old code used getName()
    public String getName() {
        return identifier;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(String programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public String getFrameworksAndLibraries() {
        return frameworksAndLibraries;
    }

    public void setFrameworksAndLibraries(String frameworksAndLibraries) {
        this.frameworksAndLibraries = frameworksAndLibraries;
    }

    public String getToolsAndTechnologies() {
        return toolsAndTechnologies;
    }

    public void setToolsAndTechnologies(String toolsAndTechnologies) {
        this.toolsAndTechnologies = toolsAndTechnologies;
    }

    /**
     * Update contact information in one shot.
     * Used by the EditAccountInteractor.
     */
    public void updateContactInfo(String newLocation,
                                  String newEmail,
                                  String newPhone) {

        if (newLocation != null) {
            this.location = newLocation;
        }
        if (newEmail != null) {
            this.email = newEmail;
        }
        if (newPhone != null) {
            this.phone = newPhone;
        }
    }

    /**
     * Update all qualifications in one shot.
     * Used by the EditAccountInteractor.
     */
    public void updateQualifications(String education,
                                     String workExperience,
                                     String projects,
                                     String skills,
                                     String programmingLanguages,
                                     String frameworksAndLibraries,
                                     String toolsAndTechnologies) {

        if (education != null) {
            this.education = education;
        }
        if (workExperience != null) {
            this.workExperience = workExperience;
        }
        if (projects != null) {
            this.projects = projects;
        }
        if (skills != null) {
            this.skills = skills;
        }
        if (programmingLanguages != null) {
            this.programmingLanguages = programmingLanguages;
        }
        if (frameworksAndLibraries != null) {
            this.frameworksAndLibraries = frameworksAndLibraries;
        }
        if (toolsAndTechnologies != null) {
            this.toolsAndTechnologies = toolsAndTechnologies;
        }
    }
}
