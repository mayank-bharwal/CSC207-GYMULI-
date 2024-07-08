package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewModelManager {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private String activeView;

    public void setActiveView(String activeView) {
        String oldView = this.activeView;
        this.activeView = activeView;
        pcs.firePropertyChange("activeView", oldView, activeView);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void firePropertyChanged() {
        pcs.firePropertyChange("activeView", null, this.activeView);
    }
}