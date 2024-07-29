package views;

import interface_adapter.ViewModelManager;
import interface_adapter.account_creation.SignupController;
import interface_adapter.account_creation.SignupState;
import interface_adapter.account_creation.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final SignupController signupController;
    private final ViewModelManager viewModelManager;

    private final JButton signUp;
    private final JButton clear;
    private final JButton login;

    public SignupView(SignupController controller, SignupViewModel signupViewModel, ViewModelManager viewModelManager) {
        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.viewModelManager = viewModelManager;
        signupViewModel.addPropertyChangeListener(this);

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

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JTextField usernameField = signupViewModel.usernameInputField;
        formPanel.add(usernameField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passwordLabel = new JLabel("Password:");
        formPanel.add(passwordLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JPasswordField passwordField = signupViewModel.passwordInputField;
        formPanel.add(passwordField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel repeatPasswordLabel = new JLabel("Repeat Password:");
        formPanel.add(repeatPasswordLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JPasswordField repeatPasswordField = signupViewModel.repeatPasswordInputField;
        formPanel.add(repeatPasswordField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel programOfStudyLabel = new JLabel("Program of Study:");
        formPanel.add(programOfStudyLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JTextField programOfStudyField = signupViewModel.programOfStudyInputField;
        formPanel.add(programOfStudyField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel bioLabel = new JLabel("Bio");
        formPanel.add(bioLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JTextArea bioTextArea = signupViewModel.bioInputField;
        formPanel.add(bioTextArea, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel ageLabel = new JLabel("Age:");
        formPanel.add(ageLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JTextField ageField = signupViewModel.ageInputField;
        formPanel.add(ageField, gbc);





        signupViewModel.programOfStudyInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                String text = signupViewModel.programOfStudyInputField.getText() + e.getKeyChar();
                currentState.setProgramOfStudy(text);
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel interest1Label = new JLabel("Interest 1:");
        formPanel.add(interest1Label, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JTextField interest1Field = signupViewModel.interest1InputField;
        formPanel.add(interest1Field, gbc);



        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel interest2Label = new JLabel("Interest 2:");
        formPanel.add(interest2Label, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JTextField interest2Field = signupViewModel.interest2InputField;
        formPanel.add(interest2Field, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 9;
        JLabel interest3Label = new JLabel("Interest 3:");
        formPanel.add(interest3Label, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        JTextField interest3Field = signupViewModel.interest3InputField;
        formPanel.add(interest3Field, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttonPanel.add(signUp);
        clear = new JButton(SignupViewModel.CLEAR_BUTTON_LABEL);
        buttonPanel.add(clear);
        login = new JButton("Go to Login");
        buttonPanel.add(login);
        formPanel.add(buttonPanel, gbc);

        signUp.addActionListener(e -> {
            SignupState currentState = signupViewModel.getState();
            signupController.execute(
                    currentState.getUsername(),
                    currentState.getPassword(),
                    currentState.getRepeatPassword(),
                    currentState.getProgramOfStudy(),
                    Arrays.asList(
                            signupViewModel.interest1InputField.getText(),
                            signupViewModel.interest2InputField.getText(),
                            signupViewModel.interest3InputField.getText()
                    ),
                    currentState.getBio(),
                    currentState.getAge()
            );
        });

        clear.addActionListener(e -> {
            signupViewModel.usernameInputField.setText("");
            signupViewModel.passwordInputField.setText("");
            signupViewModel.repeatPasswordInputField.setText("");
            signupViewModel.programOfStudyInputField.setText("");
            signupViewModel.interest1InputField.setText("");
            signupViewModel.interest2InputField.setText("");
            signupViewModel.interest3InputField.setText("");
            SignupState currentState = new SignupState();
            signupViewModel.setState(currentState);
            signupViewModel.firePropertyChanged();
        });


        login.addActionListener(e -> viewModelManager.setActiveView("LoginView"));

        signupViewModel.usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                String text = signupViewModel.usernameInputField.getText() + e.getKeyChar();
                currentState.setUsername(text);
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        signupViewModel.passwordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(signupViewModel.passwordInputField.getPassword()) + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        signupViewModel.repeatPasswordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(signupViewModel.repeatPasswordInputField.getPassword()) + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        this.add(formPanel, BorderLayout.CENTER);
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
            String errorMessage = signupViewModel.getState().getError();
            if (errorMessage != null) {
                JOptionPane.showMessageDialog(this, errorMessage, "Signup Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if ("success".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, "Account successfully created!", "Signup Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static class LabelTextPanel extends JPanel {
        public LabelTextPanel(JLabel label, JTextField textField) {
            this.setLayout(new BorderLayout());
            this.add(label, BorderLayout.WEST);
            this.add(textField, BorderLayout.CENTER);
        }
    }
}


