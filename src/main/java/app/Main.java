package app;


import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addCompanyLoggedInView()
                .addCompanyAccountView()
                .addPostJobView()
                .addPostJobUseCase()
                .addEditCompanyAccountUseCase()
                .addPullCompanyDataUseCase()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
