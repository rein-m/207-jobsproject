package use_case.login;

public class LoginOutputData {
    private final String identifier;
    private final boolean useCaseFailed;
    public LoginOutputData(String identifier, boolean useCaseFailed) {
        this.identifier = identifier;
        this.useCaseFailed = useCaseFailed;
    }
    public String getIdentifier() {
        return identifier;
    }
}
