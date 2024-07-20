package views;

import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;

public class CreateChatView extends JPanel {
    public static final String viewName = "CreateChatView";

    private final JTextField chatNameField;
    private final JTextField userField;
    private final JButton createChatButton;
    private final JButton cancelButton;

    public CreateChatView(ViewModelManager viewModelManager) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Create Chat", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel chatNameLabel = new JLabel("Chat Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(chatNameLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        chatNameField = new JTextField(20);
        formPanel.add(chatNameField, gbc);

        JLabel user1Label = new JLabel("Enter User:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(user1Label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        userField = new JTextField(20);
        formPanel.add(userField, gbc);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createChatButton = new JButton("Create Chat");
        buttonPanel.add(createChatButton);
        cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        cancelButton.addActionListener(e -> viewModelManager.setActiveView(MainView.viewName));
    }
}
