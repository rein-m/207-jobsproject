package use_case.login;

public class LoginOutputData {
    private final String identifier;
    private final String type;
    private final boolean useCaseFailed;
    public LoginOutputData(String identifier, String type, boolean useCaseFailed) {
        this.identifier = identifier;
        this.type = type;
        this.useCaseFailed = useCaseFailed;
    }
    public String getIdentifier() {
        return identifier;
    }
    public String getType() {
        return type;
    }
}
