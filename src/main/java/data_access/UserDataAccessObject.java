package data_access;

// import statements

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import entity.UserRecord;
import entity.UserFactory;
import use_case.AccountInfo.AccountInfoDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/*
 DAO for a user. Uses a JSON file for persistence, but could alternatively use Docker/Mongo.
 If extra time and improves functionality, switch to that implementation.
 Users and all of their attributes are stored in the userAccounts HashMap.
 If you need to retrieve information about a user, import this class and grab from userAccounts by username.
 e.g. userAccounts.get(username) will give you the user object.
 userAccounts.get(username).getResumes() will give you the user's list of resumes.
*/

public class UserDataAccessObject implements AccountInfoDataAccessInterface {

    private final File JSONFile; // File to store things in.
    private final UserFactory userFactory;
    private final Map<String, User> userAccounts = new HashMap<>(); // store userAccounts in a hash map.

    // The constructor for UserDataAccessObject takes only two parameters: a csv path and a userFactory.
    public UserDataAccessObject(File JSONFile, UserFactory userFactory) throws IOException {
        this.JSONFile = JSONFile;
        this.userFactory = userFactory;
        loadData(); // call the method to loadData.

    }

    public void saveData() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter write = new FileWriter(JSONFile))
        {
            gson.toJson(userAccounts.values(), write);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadData() throws IOException{
        Gson gson = new Gson();

        try(Reader reader = new FileReader(JSONFile)) {

            UserRecord[] userRecords = gson.fromJson(reader, UserRecord[].class);

            for (UserRecord r: userRecords) {
                User user = userFactory.createEntity(r.name,
                        r.username,
                        r.password,
                        r.location,
                        r.email,
                        r.phone,
                        r.resumes
                );
                userAccounts.put(user.getIdentifier(), user); // store user in userAccounts.
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Determine whether a given user exists by USERNAME.
//    @Override
    public boolean existsByName(String username) {
        return userAccounts.containsKey(username);
    }

    // Determine whether a given user exists by NAME/identifier.
//    @Override
    public boolean existsByIdentifier(String identifier) {
        return userAccounts.containsKey(identifier);
    }

    // from AccountInfoDataAccessInterface -- don't use, just so intelliJ doesn't yell at me.
//    @Override
    public void save(User user) {

    }

    // don't use. just so intelliJ doesn't yell at me.
//    @Override
    public User get(String identifier, String entityType) {
        return null;
    }

    // get a user by identifier.
    @Override
    public User get(String identifier) {
        return userAccounts.get(identifier);
    }

    // not necessary, will never call this method from user DAO but intelliJ wants it
//    @Override
    public void postJob(String title, String description) {

    }

}