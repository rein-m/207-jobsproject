package interface_adapter.loggedin;

public class LoggedinState {
    private String Appalicant_identifier;
    private boolean loggedIn;

    public String getIdentifier() {
        return Appalicant_identifier;
    }
    public void setIdentifier(String identifier) {
        this.Appalicant_identifier = identifier;
    }
    public boolean getState(Boolean loggedin) {return loggedin;}
    public void setState(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


}
