package interface_adapter.loggedin;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

public class LoggedinViewModel extends ViewModel<LoggedinState> {
    public LoggedinViewModel() {
        super("logged in");
        setState(new LoggedinState());
    }

}
