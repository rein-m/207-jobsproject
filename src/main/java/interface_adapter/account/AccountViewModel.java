package interface_adapter.account;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the account feature.
 * Views listen to this for changes.
 */
public class AccountViewModel {

    public static final String ACCOUNT_PROPERTY = "account";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private AccountState state = new AccountState();

    public AccountState getState() {
        return state;
    }

    public void setState(AccountState state) {
        this.state = state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void firePropertyChanged() {
        support.firePropertyChange(ACCOUNT_PROPERTY, null, this.state);
    }
}
