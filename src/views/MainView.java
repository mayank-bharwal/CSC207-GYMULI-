package views;

import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "MainView";

    private final ViewModelManager viewModelManager;
    private final JLabel currentUserLabel;

    public MainView(ViewModelManager viewModelManager) {
        this.viewModelManager = viewModelManager;
        this.viewModelManager.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        currentUserLabel = new JLabel();
        headerPanel.add(new JLabel("Logged in as: "));
        headerPanel.add(currentUserLabel);

        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Chats will be displayed here"), gbc);

        JButton createChatButton = new JButton("Create Chat");
        createChatButton.addActionListener(e -> viewModelManager.setActiveView(CreateChatView.viewName));
        gbc.gridy = 1;
        mainPanel.add(createChatButton, gbc);

        add(mainPanel, BorderLayout.CENTER);

        updateCurrentUser();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("currentUser".equals(evt.getPropertyName())) {
            updateCurrentUser();
        }
    }

    private void updateCurrentUser() {
        String currentUser = viewModelManager.getCurrentUser();
        currentUserLabel.setText(currentUser != null ? currentUser : "Not logged in");
    }
}

