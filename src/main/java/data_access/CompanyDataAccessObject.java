package data_access;

// import statements

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.*;
import use_case.AccountInfo.AccountInfoDataAccessInterface;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/*
 DAO for a company. Uses a JSON file for persistence, but could alternatively use Docker/Mongo.
 If extra time and improves functionality, switch to that implementation.
 Companies and all of their attributes are stored in the companyAccounts HashMap.
 If you need to retrieve information about a company, import this class and grab from companyAccounts by username.
 e.g. companyAccounts.get(username) will give you the company object associated with that
 companyAccounts.get(username).getJobs() will give you the company's list of jobs.
*/
public class CompanyDataAccessObject implements AccountInfoDataAccessInterface

{
    private final File JSONFile; // File to store things in.
    private final CompanyFactory companyFactory;
    private final Map<String, Company> companyAccounts = new HashMap<>(); // store userAccounts in a hash map.

    // The constructor for CompanyDataAccessObject takes only two parameters: a csv path and a companyFactory.
    public CompanyDataAccessObject(File JSONfile, CompanyFactory companyFactory) throws IOException {
        this.JSONFile = JSONfile;
        this.companyFactory = companyFactory;
        loadData(); // call the method to load data.
    }


    private void loadData() throws IOException {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(JSONFile)) {
            CompanyRecord[] companyRecords = gson.fromJson(reader, CompanyRecord[].class);

            for (CompanyRecord r : companyRecords) {
                Company company = companyFactory.createEntity(
                        r.identifier,
                        r.companyName,
                        r.website,
                        r.email,
                        r.number,
                        r.location,
                        r.password,
                        r.jobs
                );
                companyAccounts.put(company.getIdentifier(), company);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // saves company data -- add company argument.
    public void saveData() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter write = new FileWriter(JSONFile))
        {
            gson.toJson(companyAccounts.values(), write);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // get a company by username.
    public Entity getCompany(String name) {
        return companyAccounts.get(name);
    }

    // do not use any of the following methods -- they are all to satisy intelliJ.
    @Override
    public void save(User user) {

    }

    @Override
    public User get(String identifier, String entityType) {
        return null;
    }

    @Override
    public User get(String username) throws SQLException {
        return null;
    }

    @Override
    public void postJob(String title, String description) {
        //
    }
}
