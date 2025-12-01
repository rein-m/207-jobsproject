package interface_adapter.ResumeShit.resumeUI;

import interface_adapter.ViewModel;

public class ResumeUIViewModel extends ViewModel<ResumeUIState> {
    public ResumeUIViewModel() {
        super("ResumeUI");
        setState(new ResumeUIState());
    }

}
