package views;

import interface_adapter.account_creation.SignUpController;
import interface_adapter.account_creation.SignUpState;
import interface_adapter.account_creation.SignUpViewModel;

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
    public final String viewName = "sign up";

    private final SignUpViewModel signupViewModel;
    private final SignUpController signupController;

    private final JButton signUp;
    private final JButton cancel;
    private final JButton clear;

    public SignupView(SignUpController controller, SignUpViewModel signupViewModel) {
        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        signupViewModel.titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                signupViewModel.usernameLabel, signupViewModel.usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                signupViewModel.passwordLabel, signupViewModel.passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                signupViewModel.repeatPasswordLabel, signupViewModel.repeatPasswordInputField);

        JPanel interestsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        interestsPanel.add(signupViewModel.interest1InputField);
        interestsPanel.add(signupViewModel.interest2InputField);
        interestsPanel.add(signupViewModel.interest3InputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(SignUpViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(SignUpViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        clear = new JButton(SignUpViewModel.CLEAR_BUTTON_LABEL);
        buttons.add(clear);

        signUp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignUpState currentState = signupViewModel.getState();
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
                        }
                    }
                }
        );

        clear.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        signupViewModel.usernameInputField.setText("");
                        signupViewModel.passwordInputField.setText("");
                        signupViewModel.repeatPasswordInputField.setText("");
                        signupViewModel.interest1InputField.setText("");
                        signupViewModel.interest2InputField.setText("");
                        signupViewModel.interest3InputField.setText("");
                        SignUpState currentState = new SignUpState();
                        signupViewModel.setState(currentState);
                        signupViewModel.firePropertyChanged();
                    }
                }
        );

        cancel.addActionListener(this);

        signupViewModel.usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        String text = signupViewModel.usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        signupViewModel.passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        currentState.setPassword(new String(signupViewModel.passwordInputField.getPassword()) + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        signupViewModel.repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(new String(signupViewModel.repeatPasswordInputField.getPassword()) + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(signupViewModel.titleLabel);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(new LabelTextPanel(signupViewModel.interestsLabel, new JTextField()));
        this.add(interestsPanel);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignUpState state = (SignUpState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
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