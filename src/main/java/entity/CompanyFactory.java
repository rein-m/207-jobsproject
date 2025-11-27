package entity;

public class CompanyFactory extends GenericFactory<Company> {
    @Override
    public Company createEntity(String name, String password) {
        return new Company(name, password);
    }
}
