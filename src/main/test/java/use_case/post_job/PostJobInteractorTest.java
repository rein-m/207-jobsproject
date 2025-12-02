package use_case.post_job;

import data_access.CompanyDataAccessObject;
import entity.Company;
import entity.CompanyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.company_account.CompanyAccountViewModel;
import interface_adapter.company_loggedin.CompanyLoggedInState;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;
import interface_adapter.post_job.PostJobController;
import interface_adapter.post_job.PostJobPresenter;
import interface_adapter.post_job.PostJobViewModel;
import use_case.post_job.PostJobInputBoundary;
import use_case.post_job.PostJobInteractor;
import use_case.post_job.PostJobOutputBoundary;
import view.CompanyAccountView;
import view.PostJobView;
import view.company_loggedin.CompanyLoggedInView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostJobInteractorTest {

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("Test Failed: " + message);
        }
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Test Failed: " + message + " Expected: [" + expected + "], Actual: [" + actual + "]");
        }
    }

    private static void testExecuteSuccessPath() {


        CompanyLoggedInView companyLoggedInView;
        final CompanyLoggedInViewModel companyLoggedInViewModel = new CompanyLoggedInViewModel();
        CompanyAccountView companyAccountView;
        final CompanyAccountViewModel companyAccountViewModel  = new CompanyAccountViewModel();
        final ViewManagerModel viewManagerModel = new ViewManagerModel();

        final CompanyFactory companyFactory = new CompanyFactory();
        File file = new File("/Users/ethan/IdeaProjects/207-jobsproject/src/main/java/data_persistence.json");
        CompanyDataAccessObject companyDataAccessObject;

        {
            try {
                companyDataAccessObject = new CompanyDataAccessObject(file, companyFactory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        final PostJobViewModel postJobViewModel = new PostJobViewModel();

        final PostJobOutputBoundary postJobPresenter =
                new PostJobPresenter(companyLoggedInViewModel, viewManagerModel);
        final PostJobInputBoundary postJobInteractor = new PostJobInteractor(postJobPresenter, companyDataAccessObject);
        PostJobController postJobController = new PostJobController(postJobInteractor);

        postJobController.execute("comp002", "Software Engineer", "Intern", "Toronto");

        // ASSERTIONS
        System.out.println("Running testExecuteSuccessPath...");

        // Verify Company Entity's jobs list was updated
        Company company = companyDataAccessObject.getCompany("comp002");
        assertEquals(3, company.getJobs().size(), "Jobs list size check failed. Should be 3.");
        ArrayList<String> companyJobs = company.getJobs().get(company.getJobs().size() - 1);
        assertEquals("Software Engineer", companyJobs.get(0), "Job Title mutation check failed.");
        assertEquals("Intern", companyJobs.get(1), "Job Description mutation check failed.");
        assertEquals("Toronto", companyJobs.get(2), "Job Location mutation check failed.");
        System.out.println("  -> Company Data Mutation Passed.");

        // Verify CompanyLoggedInState's jobs list was updated
        CompanyLoggedInState state = companyLoggedInViewModel.getState();
        assertEquals(3, state.getJobListings().size(), "Jobs list size check failed. Should be 3.");
        ArrayList<String> postedJob = state.getJobListings().get(state.getJobListings().size() - 1);
        assertEquals("Software Engineer", postedJob.get(0), "Job Title mutation check failed.");
        assertEquals("Intern", postedJob.get(1), "Job Description mutation check failed.");
        assertEquals("Toronto", postedJob.get(2), "Job Location mutation check failed.");
        System.out.println("  -> Company Logged In State Data Mutation Passed.");

    }

    private static void testSwitchView() {
        CompanyLoggedInView companyLoggedInView;
        final CompanyLoggedInViewModel companyLoggedInViewModel = new CompanyLoggedInViewModel();
        CompanyAccountView companyAccountView;
        final CompanyAccountViewModel companyAccountViewModel  = new CompanyAccountViewModel();
        final ViewManagerModel viewManagerModel = new ViewManagerModel();

        final CompanyFactory companyFactory = new CompanyFactory();
        File file = new File("/Users/ethan/IdeaProjects/207-jobsproject/src/main/java/data_persistence.json");
        CompanyDataAccessObject companyDataAccessObject;

        {
            try {
                companyDataAccessObject = new CompanyDataAccessObject(file, companyFactory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        final PostJobViewModel postJobViewModel = new PostJobViewModel();

        final PostJobOutputBoundary postJobPresenter =
                new PostJobPresenter(companyLoggedInViewModel, viewManagerModel);
        final PostJobInputBoundary postJobInteractor = new PostJobInteractor(postJobPresenter, companyDataAccessObject);
        PostJobController postJobController = new PostJobController(postJobInteractor);

        postJobController.switchToCompanyLoggedInView();

        // ASSERTIONS
        System.out.println("\nRunning testSwitchView...");
        assertEquals("company logged in", viewManagerModel.getState(),"Switch View check failed. ViewManagerModel should have state set as company logged in.");
        System.out.println("Test testSwitchView PASSED.");
    }

    public static void main(String[] args) {
        try {
            System.out.println("Starting PostJobInteractor Standalone Tests...");
            testExecuteSuccessPath();
            testSwitchView();
            System.out.println("\nAll Standalone Tests Completed SUCCESSFULLY.");
        } catch (AssertionError e) {
            System.err.println("\n" + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("\nAn unexpected error occurred during testing: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
