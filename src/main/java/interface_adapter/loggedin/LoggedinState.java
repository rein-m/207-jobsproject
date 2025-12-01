package interface_adapter.loggedin;

public class LoggedinState {
    private String identifier;
    private String password;
    private String loginError;

    private boolean loggedIn;

    public String getIdentifier() {
        return identifier;
    }
    public String getPassword() {
        return password;
    }
    public String getLoginError() {
        return loginError;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void  getState(Boolean loggedin){this.loggedIn = true;}



}
