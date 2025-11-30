package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addUserAccountInfoView()
                .addResumeView()
                .addResumeUIUseCase()
                .addAccountInfoUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
