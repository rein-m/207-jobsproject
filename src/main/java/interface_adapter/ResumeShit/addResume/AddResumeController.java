package interface_adapter.ResumeShit.addResume;
import use_case.ResumeShit.addResume.AddResumeInputBoundary;
import use_case.ResumeShit.addResume.AddResumeInputData;

public class AddResumeController {
    private final AddResumeInputBoundary addResumeInteractor;

    public AddResumeController(AddResumeInputBoundary addResumeInteractor) {
        this.addResumeInteractor = addResumeInteractor;
    }

    public void execute(String filePath, String userIdentifier) {
        final AddResumeInputData addResumeInputData = new AddResumeInputData(
                filePath, userIdentifier);
        addResumeInteractor.execute(addResumeInputData);
    }
}
