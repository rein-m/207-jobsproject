package use_case.signup;
import entity.Entity;


public class SignupInputData {

    private final String entityType;
    private final String identifier;
    private final String password1;
    private final String password2;

    public SignupInputData(String entityType, String identifier, String password1, String password2) {
        this.entityType = entityType;
        this.identifier = identifier;
        this.password1 = password1;
        this.password2 = password2;
    }
    String getEntityType() {
        return entityType;
    }

    String getIdentifier() {
        return identifier;
    }

    String getPassword() {
        return password1;
    }
    String getRepeatPassword() {
        return password2;
    }

}