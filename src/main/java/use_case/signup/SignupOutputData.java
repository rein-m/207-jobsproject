package use_case.signup;

public class SignupOutputData {
    private final String identifier;
    private final boolean useCaseFailed;

    public SignupOutputData(String identifier, boolean useCaseFailed) {
        this.identifier = identifier;
        this.useCaseFailed = useCaseFailed;
    }

    public String getIdentifier() {
        return identifier;
    }
    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
