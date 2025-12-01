package entity;

public class CompanyFactory {
    public static Company createEntity(String identifier, String companyName, String website, String email, String number,
                                       String location, String password) {
        return new Company(identifier, companyName, website, email, number, location, password);
    }
}
