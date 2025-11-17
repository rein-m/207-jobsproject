package interface_adapter.post_job;

import interface_adapter.ViewModel;

public class PostJobViewModel extends ViewModel<PostJobState> {

    public PostJobViewModel() {
        super("post job");
        setState(new PostJobState());
    }
}
