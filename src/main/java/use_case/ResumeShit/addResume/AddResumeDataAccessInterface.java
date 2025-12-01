package use_case.ResumeShit.addResume;

import entity.User;

public interface AddResumeDataAccessInterface {
    User getUser(String username);

    void addResume(User user, String resume);

}
