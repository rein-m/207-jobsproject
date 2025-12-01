package use_case.login;


public class LoginInputData {

    private final String entityType;
    private final String identifier;
    private final String password;

    public LoginInputData(String entityType, String identifier, String password) {
        this.entityType = entityType;
        this.identifier = identifier;
        this.password = password;
    }
    String getEntityType() {
        return entityType;
    }

    String getIdentifier() {
        return identifier;
    }

    String getPassword() {
        return password;
    }

}