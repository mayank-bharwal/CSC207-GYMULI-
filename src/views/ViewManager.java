package views;

import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final JPanel views;
    private final CardLayout cardLayout;
    private final ViewModelManager viewModelManager;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewModelManager viewModelManager) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewModelManager = viewModelManager;
        this.viewModelManager.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("activeView".equals(evt.getPropertyName())) {
            cardLayout.show(views, viewModelManager.getActiveView());
        }
    }
}
