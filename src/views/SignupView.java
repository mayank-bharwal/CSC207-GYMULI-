package views;


import interface_adapter.SignupViewModel;
import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements PropertyChangeListener {
    public final String viewName = "SignupView";
    private final SignupViewModel viewModel;
    private final ViewModelManager viewModelManager;

    public SignupView(ViewModelManager viewModelManager, SignupViewModel viewModel) {
        this.viewModelManager = viewModelManager;
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Signup");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordField, gbc);

        JLabel programOfStudyLabel = new JLabel("Program(s) of Study:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(programOfStudyLabel, gbc);

        JTextField programOfStudyField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(programOfStudyField, gbc);

        JLabel interestsLabel = new JLabel("Interests:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(interestsLabel, gbc);

        JPanel interestsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JTextField interest1Field = new JTextField(10);
        JTextField interest2Field = new JTextField(10);
        JTextField interest3Field = new JTextField(10);
        interestsPanel.add(interest1Field);
        interestsPanel.add(interest2Field);
        interestsPanel.add(interest3Field);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(interestsPanel, gbc);

        gbc.gridwidth = 1;


        JButton signupButton = new JButton("Signup");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        signupButton.addActionListener(e -> {
            // Add logic to handle signup action
            System.out.println("Signing up...");
        });
        formPanel.add(signupButton, gbc);

        JButton loginButton = new JButton("Go to Login");
        loginButton.addActionListener(e -> viewModelManager.setActiveView("LoginView"));
        gbc.gridy = 10;
        formPanel.add(loginButton, gbc);

        add(formPanel, BorderLayout.CENTER);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle view model property changes
    }
}