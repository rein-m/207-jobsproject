//package data_access;
//
//// import statements
//
//import entity.*;
//import use_case.login.LoginUserDataAccessInterface;
//import use_case.logout.LogoutUserDataAccessInterface;
//import use_case.signup.SignupUserDataAccessInterface;
//import java.io.*;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///*
// DAO for a company.
//*/
//
//public class CompanyDataAccessObject implements LoginUserDataAccessInterface, LogoutUserDataAccessInterface,
//        SignupUserDataAccessInterface {
//    private static final String HEADER = "identifier,companyName,website,email,number,location,password"; // company info
//
//
//    private final File csvFile; // File to store things in.
//    private final Map<String, Integer> headers = new LinkedHashMap<>(); // store headers in a linked hash map.
//    private final Map<String, Company> companyAccounts = new HashMap<>(); // store companyAccounts in a hash map.
//
//    // The constructor for CompanyDataAccessObject takes only two parameters: a csv path and a companyFactory.
//    public CompanyDataAccessObject(File csvPath, CompanyFactory companyFactory) {
//        csvFile = csvPath;
//        headers.put("identifier", 0);
//        headers.put("companyName", 1);
//        headers.put("website", 2);
//        headers.put("email", 3);
//        headers.put("number", 4);
//        headers.put("location", 5);
//        headers.put("password", 6);
//
//        if (csvFile.length() == 0) {
//            save();
//        }
//
//        else {
//            try(BufferedReader readFile = new BufferedReader(new FileReader(csvFile))) {
//                final String header = readFile.readLine();
//
//                if (!header.equals(HEADER)) {
//                    throw new RuntimeException(String.format("header should be %n: %s%n, but was: %n%s", HEADER, header));
//                }
//
//                String row;
//                while ((row = readFile.readLine()) != null) {
//                    final String[] columns = row.split(","); // split columns by ,
//
//                    // company attributes
//                    final String identifier = String.valueOf(columns[headers.get("identifier")]);
//                    final String companyName = String.valueOf(columns[headers.get("companyName")]);
//                    final String website = String.valueOf(columns[headers.get("website")]);
//                    final String email = String.valueOf(columns[headers.get("email")]);
//                    final String number = String.valueOf(columns[headers.get("number")]);
//                    final String location = String.valueOf(columns[headers.get("location")]);
//                    final String password = String.valueOf(columns[headers.get("password")]);
//
//
//                    final Company company = CompanyFactory.createEntity(identifier, companyName, website, email, number,
//                            location, password);
//                    companyAccounts.put(companyName, company); // maps companyName to company information.
//                }
//
//            }
//            catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public void save() // changed from private (maybe back)
//    {
//        final BufferedWriter writer;
//        try {
//            writer = new BufferedWriter(new FileWriter(csvFile));
//            writer.write(String.join(",", headers.keySet()));
//            writer.newLine();
//
//            for (Company company : companyAccounts.values()) {
//                final String line = String.format("%s, %s, %s, %s, %s, %s, %s", company.getIdentifier(),
//                        company.getCompanyName(), company.getWebsite(), company.getEmail(), company.getNumber(),
//                        company.getLocation(), company.getPassword());
//                writer.write(line);
//                writer.newLine();
//            }
//            writer.close();
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // Determine whether a given company exists by COMPANYNAME.
//    @Override
//    public boolean existsByName(String companyName) {
//        return companyAccounts.containsKey(companyName);
//    }
//
//    // Determine whether a given company exists by NAME/identifier.
//    @Override
//    public boolean existsByIdentifier(String identifier) {
//        return companyAccounts.containsKey(identifier);
//    }
//
//    // Save the current company.
//    @Override //overrides the save() from LoginUserDataAccessInterface
//    public void save(Company company) {
//        companyAccounts.put(company.getIdentifier(), company);
//        this.save();
//    }
//
//    @Override
//    public Company get(String identifier, String entityType) {
//        return companyAccounts.get(identifier);
//    }
//
//    // get a user by identifier.
//    @Override // overrides the get method from LoginUserDataAccesInterface
//    public Company get(String identifier) {
//        return companyAccounts.get(identifier);
//    }
//
//}
//
//}
