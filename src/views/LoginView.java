package views;

import interface_adapter.ViewModelManager;
import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "LoginView";

    private final LoginViewModel loginViewModel;
    private final LoginController loginController;
    private final ViewModelManager viewModelManager;

    private final JButton loginButton;
    private final JButton cancelButton;
    private final JButton signupButton;

    public LoginView(LoginController loginController, LoginViewModel loginViewModel, ViewModelManager viewModelManager) {
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        this.viewModelManager = viewModelManager;
        loginViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Login");
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

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JTextField usernameField = loginViewModel.usernameInputField;
        formPanel.add(usernameField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Password:");
        formPanel.add(passwordLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JPasswordField passwordField = loginViewModel.passwordInputField;
        formPanel.add(passwordField, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        buttonPanel.add(loginButton);
        cancelButton = new JButton("Clear");
        buttonPanel.add(cancelButton);
        signupButton = new JButton("Go to Signup");
        buttonPanel.add(signupButton);
        formPanel.add(buttonPanel, gbc);

        loginButton.addActionListener(e -> login());
        cancelButton.addActionListener(e -> clearFields());
        signupButton.addActionListener(e -> viewModelManager.setActiveView(SignupView.viewName));

        loginViewModel.usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                String text = loginViewModel.usernameInputField.getText() + e.getKeyChar();
                currentState.setUsername(text);
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        loginViewModel.passwordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(loginViewModel.passwordInputField.getPassword()) + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.add(formPanel, BorderLayout.CENTER);
    }

    private void login() {
        LoginState currentState = loginViewModel.getState();
        loginController.login(currentState.getUsername(), currentState.getPassword());
    }

    private void clearFields() {
        loginViewModel.usernameInputField.setText("");
        loginViewModel.passwordInputField.setText("");
        LoginState currentState = new LoginState();
        loginViewModel.setState(currentState);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("activeView".equals(evt.getPropertyName())) {
            if (viewModelManager.getActiveView().equals(viewName)) {
                CardLayout cl = (CardLayout) getParent().getLayout();
                cl.show(getParent(), viewName);
            }
        }
        if ("generalError".equals(evt.getPropertyName())) {
            String errorMessage = loginViewModel.getState().getPasswordError();
            if (errorMessage != null) {
                JOptionPane.showMessageDialog(this, errorMessage, "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}


