package data_access;

// import statements

import entity.Entity;
import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 DAO for a user. Uses a file for persistence, but could alternatively use Docker/Mongo.
 If extra time and improves functionality, switch to that implementation.
*/

public class UserDataAccessObject implements LoginUserDataAccessInterface,
                                            LogoutUserDataAccessInterface,
                                            SignupUserDataAccessInterface {

    private static final String HEADER = "identifier,username,password,location,email,phone"; // user info


    private final File csvFile; // File to store things in.
    private final Map<String, Integer> headers = new LinkedHashMap<>(); // store headers in a linked hash map.
    private final Map<String, User> userAccounts = new HashMap<>(); // store userAccounts in a hash map.

    // The constructor for UserDataAccessObject takes only two parameters: a csv path and a userFactory.
    public UserDataAccessObject(File csvPath, UserFactory userFactory) throws FileNotFoundException {
        csvFile = csvPath;
        headers.put("identifier", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("location", 3);
        headers.put("email", 4);
        headers.put("phone", 5);

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
                    final String username = String.valueOf(columns[headers.get("username")]);
                    final String identifier = String.valueOf(columns[headers.get("identifier")]);
                    final String password = String.valueOf(columns[headers.get("password")]);
                    final String location = String.valueOf(columns[headers.get("location")]);
                    final String email = String.valueOf(columns[headers.get("email")]);
                    final String phone = String.valueOf(columns[headers.get("phone")]);

                    final User user = UserFactory.createEntity(identifier, username, password, location, email, phone);
                    userAccounts.put(username, user); // maps username to user information.
                }

            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void save() // changed from private (maybe back)
    {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user: userAccounts.values()) {
                final String line = String.format("%s, %s, %s, %s, %s", user.getIdentifier(), user.getUsername(),
                        user.getPassword(), user.getLocation(), user.getEmail(), user.getPhone());
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   // Determine whether a given user exists by USERNAME.
    @Override
    public boolean existsByName(String username) {
        return userAccounts.containsKey(username);
    }

    // Determine whether a given user exists by NAME/identifier.
    @Override
    public boolean existsByIdentifier(String identifier) {
        return userAccounts.containsKey(identifier);
    }

    // Save the current user.
    @Override //overrides the save() from LoginUserDataAccessInterface
    public void save(User user) {
        userAccounts.put(user.getIdentifier(), user);
        this.save();
    }

    @Override
    public User get(String identifier, String entityType) {
        return userAccounts.get(identifier);
    }

    // get a user by identifier.
    @Override // overrides the get method from LoginUserDataAccesInterface
    public User get(String identifier) {
        return userAccounts.get(identifier);
    }

}
