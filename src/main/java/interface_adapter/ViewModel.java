package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewModel<S> {

    private final String viewName;

    private S state;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() { return this.viewName; }

    public S getState() { return this.state; }

    public void setState(S state) { this.state = state; }

    //method to add a listener to the observer's propery change support
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener); }

    //method to alert the observer
    public void firePropertyChange() {
        this.support.firePropertyChange("state", null, this.state); }

    public void firePropertyChange(String propertyName) {
        this.support.firePropertyChange(propertyName, null, this.state);
    }
}
