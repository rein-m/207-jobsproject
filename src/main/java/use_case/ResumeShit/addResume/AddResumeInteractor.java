package use_case.ResumeShit.addResume;



import data_access.UserDataAccessObject;
import entity.User;
import interface_adapter.ResumeShit.addResume.AddResumePresenter;


// LEFT OF AT TRYING TO ADD TEH DEPENDENCIES THAT ARE ON THE CHATGPT ON YOUR RIGHT DESKTOP
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class AddResumeInteractor {
    private final AddResumePresenter accountInfoPresenter;
    private final UserDataAccessObject accountInfoDataAccessInterface;

    public AddResumeInteractor(
            AddResumePresenter accountInfoPresenter,
            UserDataAccessObject accountInfoDataAccessInterface) {
        this.accountInfoPresenter = accountInfoPresenter;
        this.accountInfoDataAccessInterface = accountInfoDataAccessInterface;
    }

    public void execute(AddResumeInputData addResumeInputData) throws IOException {
        User user = accountInfoDataAccessInterface.userAccounts.get(addResumeInputData.userIdentifier);

        user.addResume(extractText(addResumeInputData.filepath));
        accountInfoDataAccessInterface.saveData();
    }

    public static String extractText(String pdfPath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

}
