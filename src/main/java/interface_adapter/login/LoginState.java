package interface_adapter.login;

public class LoginState {
    private String identifier;
    private String password;
    private String loginError;

    public String getIdentifier() {
        return identifier;
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

}
