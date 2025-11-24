package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.company_account.CompanyAccountViewModel;
import interface_adapter.company_loggedin.CompanyLoggedInViewModel;
import interface_adapter.post_job.PostJobController;
import interface_adapter.post_job.PostJobPresenter;
import interface_adapter.post_job.PostJobViewModel;
import use_case.post_job.PostJobInteractor;
import view.CompanyAccountView;
import view.company_loggedin.CompanyLoggedInView;
import view.PostJobView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addCompanyLoggedInView()
                .addCompanyAccountView()
                .addPostJobView()
                .addPostJobUseCase()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
