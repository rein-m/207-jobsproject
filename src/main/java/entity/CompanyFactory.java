package entity;

public class CompanyFactory extends GenericFactory<Company> {
    public static Company createEntity(String name, String password) {
        return new Company(name, password);
    }
}
