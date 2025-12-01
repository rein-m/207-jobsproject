package interface_adapter.ResumeShit.resumeUI;

import entity.User;

public class ResumeUIState {

    private User user;

    public User getUser() {return user;}

    // Pretty sure this means I need to set the user or some shit up when I am making the shit in the appbuilder
    public void setUser(User user) {this.user = user;}
}
