package use_case.ResumeShit.ResumeUI;
import interface_adapter.ResumeShit.resumeUI.ResumeUIPresenter;

public class ResumeUIInteractor {
    private final ResumeUIPresenter resumeUIPresenter;

    public ResumeUIInteractor(ResumeUIPresenter resumeUIOutputBoundary) {
        this.resumeUIPresenter = resumeUIOutputBoundary;
    }

    public void execute(String userIdentifier){
        resumeUIPresenter.prepareResumeUIView(userIdentifier);
        }
    }
