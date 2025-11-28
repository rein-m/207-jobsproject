package data_access;

// import statements

import entity.Company;
import entity.CompanyFactory;
import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.post_job.PostJobUserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 DAO for a company, also using files to persist data.
*/

public class CompanyDataAccessObject implements LoginUserDataAccessInterface,
                                                PostJobUserDataAccessInterface,
                                                    LogoutUserDataAccessInterface
{
    private static final String HEADER = "identifier,password"; // user info


    private final File csvFile; // File to store things in.
    private final Map<String, Integer> headers = new LinkedHashMap<>(); // store headers in a linked hash map.
    private final Map<String, Company> companyAccounts = new HashMap<>(); // store userAccounts in a hash map.

    // The constructor for CompanyDataAccessObject takes only two parameters: a csv path and a companyFactory.
    public CompanyDataAccessObject(File csvPath, UserFactory userFactory) throws FileNotFoundException {
        csvFile = csvPath;
        headers.put("identifier", 0);
        headers.put("password", 1);

        if (csvFile.length() == 0) {
            save();
        }

        else {
            try(BufferedReader readFile = new BufferedReader(new FileReader(csvFile))) {
                final String header = readFile.readLine();

                if (!header.equals(HEADER)) {
                    throw new RuntimeException(String.format("header should be %n: %s%n, but was: %n%s", HEADER, header));
                }

                String row;
                while ((row = readFile.readLine()) != null) {
                    final String[] columns = row.split(","); // split columns by ,

                    // user attributes
                    final String identifier = String.valueOf(columns[headers.get("identifier")]);
                    final String password = String.valueOf(columns[headers.get("password")]);

                    final Company company = CompanyFactory.createEntity(identifier, password);
                    companyAccounts.put(identifier, company); // maps identifier/company name to company
                }

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    public void save() // changed from private (maybe back)
    {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Company company: companyAccounts.values()) {
                final String line = String.format("%s, %s, %s, %s, %s", company.getIdentifier(), company.getPassword());
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // no purpose, just added so that intelliJ stops getting mad at me.
    @Override
    public void save(User user) {

    }

    // same. Want intelliJ to stop yapping at me. Don't use.
    @Override
    public User get(String identifier, String entityType) {
        return null;
    }

    // Similarly, intelliJ made me put this here... Don't use.
    public void save(Company company) {

    }

    // get company method return identifier/company name.
    @Override
    public String get(String identifier) {
        return Company.getIdentifier();
    }

    // not sure what to do with this postJob method...?
    @Override
    public void postJob(String title, String description) {

    }
}
