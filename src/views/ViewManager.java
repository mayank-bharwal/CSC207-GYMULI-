package views;

import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;

public class ViewManager {
    public ViewManager(JPanel views, CardLayout cardLayout, ViewModelManager viewManagerModel) {
        viewManagerModel.addPropertyChangeListener(evt -> {
            String newViewName = (String) evt.getNewValue();
            cardLayout.show(views, newViewName);
        });
    }
}