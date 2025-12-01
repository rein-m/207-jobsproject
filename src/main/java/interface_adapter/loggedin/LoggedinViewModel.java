package interface_adapter.loggedin;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

public class LoggedinViewModel extends ViewModel<LoggedinState> {
    public LoggedinViewModel() {
        super("log in");
        setState(new LoggedinState());
    }

}
