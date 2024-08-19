package views;

import interface_adapter.ViewModelManager;
import interface_adapter.make_chat.CreateChatController;
import interface_adapter.make_chat.CreateChatState;
import interface_adapter.make_chat.CreateChatViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateChatView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "CreateChatView";

    private final JTextField chatNameField;
    private final JTextField userField;
    private final JButton createChatButton;
    private final JButton cancelButton;
    private final CreateChatViewModel createChatViewModel;
    private final CreateChatController createChatController;
    private final ViewModelManager viewModelManager;

    public CreateChatView(ViewModelManager viewModelManager, CreateChatViewModel createChatViewModel, CreateChatController createChatController) {
        this.viewModelManager = viewModelManager;
        this.createChatViewModel = createChatViewModel;
        this.createChatController = createChatController;
        this.createChatViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Create Chat", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
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

        JLabel user2Label = new JLabel("Enter User:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(user2Label, gbc);

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
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        createChatButton.addActionListener(e -> createChatController.createChat(
                chatNameField.getText(), viewModelManager.getCurrentUser().getUsername(), userField.getText()
        ));
        cancelButton.addActionListener(e -> viewModelManager.setActiveView(MainView.viewName));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreateChatState state = createChatViewModel.getState();
        if (state.isSuccess()) {
            System.out.println("chat created successfully");
            JOptionPane.showMessageDialog(this, "Chat created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            chatNameField.setText("");
            userField.setText("");
            viewModelManager.setActiveView(MainView.viewName);
        } else if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


