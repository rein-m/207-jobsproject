package interface_adapter.ResumeShit.resumeUI;
import use_case.ResumeShit.ResumeUI.ResumeUIInputBoundary;
import use_case.ResumeShit.ResumeUI.ResumeUIInteractor;

public class ResumeUIControler {
    private final ResumeUIInteractor resumeUIInteractor;

    public ResumeUIControler(ResumeUIInteractor resumeUIInteractor) {
        this.resumeUIInteractor = resumeUIInteractor;
    }

    public void execute() {
        resumeUIInteractor.execute();
    }
}
