package views;

import entity.User;
import interface_adapter.ViewModelManager;
import interface_adapter.account_creation.SignupController;
import interface_adapter.account_creation.SignupState;
import interface_adapter.account_creation.SignupViewModel;
import interface_adapter.update_profile.UpdateProfileController;
import interface_adapter.update_profile.UpdateProfilePresenter;
import interface_adapter.update_profile.UpdateProfileState;
import interface_adapter.update_profile.UpdateProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class UpdateProfileView  extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "UpdateProfileView";

    private final UpdateProfileViewModel updateProfileViewModel;
    private final UpdateProfileController updateProfileController;
    private final ViewModelManager viewModelManager;


    private final JTextField currentUsernameField;
    private final JPasswordField currentPasswordField;
    private final JTextField ageField;
    private final JPasswordField passwordField;
    private final JTextField usernameField;
    private final JTextField programOfStudyField;
    private final JTextArea bioField;
    private final JTextField interest1Field;
    private final JTextField interest2Field;
    private final JTextField interest3Field;

    private final JLabel currentUsernameErrorLabel;
    private final JLabel currentPasswordErrorLabel;
   private final JLabel usernameErrorLabel;

    private final JButton updateButton;
    private final JButton clearButton;
    private final JButton backButton;

    /**
     * Constructs an UpdateProfileView.
     *
     * @param updateProfileViewModel The view model for the update profile view.
     * @param Controller             The controller for updating profiles.
     * @param viewModelManager       The manager for the view models.
     */

    public UpdateProfileView(UpdateProfileViewModel updateProfileViewModel, UpdateProfileController Controller,
                             ViewModelManager viewModelManager){
        this.updateProfileViewModel = updateProfileViewModel;
        this.updateProfileController = Controller;
        this.viewModelManager = viewModelManager;
        updateProfileViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Reduce padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel(UpdateProfileViewModel.TITLE_LABEL, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setPreferredSize(new Dimension(800, 40));
        this.add(titleLabel, BorderLayout.NORTH);

        // Set preferred size for the text fields
        Dimension textFieldSize = new Dimension(150, 25);



        addField(formPanel, gbc, UpdateProfileViewModel.CURRENT_USERNAME_LABEL, currentUsernameField = new JTextField(), currentUsernameErrorLabel = new JLabel(), 0, textFieldSize);
        addField(formPanel, gbc, UpdateProfileViewModel.CURRENT_PASSWORD_LABEL, currentPasswordField = new JPasswordField(), currentPasswordErrorLabel = new JLabel(), 1, textFieldSize);
        addField(formPanel, gbc, UpdateProfileViewModel.USERNAME_LABEL, usernameField = new JTextField(), usernameErrorLabel = new JLabel(), 2, textFieldSize);
        addField(formPanel, gbc, UpdateProfileViewModel.PASSWORD_LABEL, passwordField = new JPasswordField(), null, 3, textFieldSize);
        addField(formPanel, gbc, UpdateProfileViewModel.AGE_LABEL, ageField = new JTextField(), null, 4, textFieldSize);
        addField(formPanel, gbc, UpdateProfileViewModel.PROGRAM_OF_STUDY_LABEL, programOfStudyField = new JTextField(), null, 5, textFieldSize);
        addField(formPanel, gbc, UpdateProfileViewModel.BIO_LABEL, bioField = new JTextArea(3, 15), null, 6, textFieldSize);  // Adjust bio field separately
        addField(formPanel, gbc, UpdateProfileViewModel.INTEREST1_LABEL, interest1Field = new JTextField(), null, 7, textFieldSize);
        addField(formPanel, gbc, UpdateProfileViewModel.INTEREST2_LABEL, interest2Field = new JTextField(), null, 8, textFieldSize);
        addField(formPanel, gbc, UpdateProfileViewModel.INTEREST3_LABEL, interest3Field = new JTextField(), null, 9, textFieldSize);



        currentUsernameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                UpdateProfileState currentState = updateProfileViewModel.getState();
                String text = currentUsernameField.getText() + e.getKeyChar();
                currentState.setCurrentUsername(text);
                updateProfileViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        currentPasswordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                UpdateProfileState currentState = updateProfileViewModel.getState();
                currentState.setCurrentPassword(new String(currentPasswordField.getPassword()) + e.getKeyChar());
                updateProfileViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        usernameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                UpdateProfileState currentState = updateProfileViewModel.getState();
                String text = usernameField.getText() + e.getKeyChar();
                currentState.setUsername(text);
                updateProfileViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                UpdateProfileState currentState = updateProfileViewModel.getState();
                currentState.setPassword(new String(passwordField.getPassword()) + e.getKeyChar());
                updateProfileViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        bioField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                UpdateProfileState currentState = updateProfileViewModel.getState();
                String text = bioField.getText() + e.getKeyChar();
                currentState.setBio(text);
                updateProfileViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {



            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        ageField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                UpdateProfileState currentState = updateProfileViewModel.getState();
                Integer newText = Integer.valueOf(ageField.getText() + e.getKeyChar());
                currentState.setAge(newText);
                updateProfileViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        programOfStudyField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                UpdateProfileState currentState = updateProfileViewModel.getState();
                String text = programOfStudyField.getText() + e.getKeyChar();
                currentState.setProgramOfStudy(text);
                updateProfileViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });



        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 10;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        updateButton = new JButton("Update");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        // Set preferred size for the buttons
        Dimension buttonSize = new Dimension(100, 25);
        updateButton.setPreferredSize(buttonSize);
        clearButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        formPanel.add(buttonPanel, gbc);

        this.add(formPanel, BorderLayout.CENTER);

        updateButton.addActionListener(e ->{
            System.out.println("button clicked");
            UpdateProfileState currentState = updateProfileViewModel.getState();
            updateProfileController.update(
                    currentState.getCurrentUsername(),
                    currentState.getCurrentPassword(),
                    currentState.getUsername(),
                    currentState.getPassword(),
                    currentState.getBio(),
                    currentState.getAge(),
                    currentState.getProgramOfStudy(),
                Arrays.asList(
                        interest1Field.getText(),
                        interest2Field.getText(),
                        interest3Field.getText()
                ));

            if (currentState.getError() == null) { // Assume no error means success
                viewModelManager.firePropertyChanged("profileUpdated", null, currentState.getUsername());
            }

        });
        clearButton.addActionListener(e -> clearFields());
        backButton.addActionListener(e -> viewModelManager.setActiveView(MainView.viewName));


    }

    /**
     * Adds a labeled field to the given panel.
     *
     * @param panel      The panel to add the field to.
     * @param gbc        The GridBagConstraints for the layout.
     * @param label      The label for the field.
     * @param field      The field component.
     * @param errorLabel The error label for the field.
     * @param y          The y position in the layout.
     * @param size       The preferred size of the field.
     */

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, JLabel errorLabel, int y, Dimension size) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        field.setPreferredSize(size);  // Set preferred size for the field
        panel.add(field, gbc);

        if (errorLabel != null) {
            gbc.gridx = 2;
            gbc.gridy = y;
            gbc.anchor = GridBagConstraints.WEST;
            errorLabel.setForeground(Color.RED);
            panel.add(errorLabel, gbc);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Clears all input fields.
     */

    private void clearFields() {
        usernameField.setText("");
        currentPasswordField.setText("");
        ageField.setText("");
        passwordField.setText("");
        ageField.setText("");
        bioField.setText("");
        interest1Field.setText("");
        interest2Field.setText("");
        interest3Field.setText("");
        UpdateProfileState currentState = new UpdateProfileState();
        updateProfileViewModel.setState(currentState);
        updateProfileViewModel.firePropertyChanged();}


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        System.out.println("Error handling");

        if ("activeView".equals(evt.getPropertyName())) {
            System.out.println("Active view changed to: " + evt.getNewValue());
            if (viewModelManager.getActiveView().equals(viewName)) {
                CardLayout cl = (CardLayout) getParent().getLayout();
                cl.show(getParent(), viewName);
                prePopulate();


        }}
        if ("generalError".equals(evt.getPropertyName())) {
            String errorMessage = updateProfileViewModel.getState().getError();
            System.out.println(322);
            System.out.println(errorMessage != null);
            if (errorMessage != null) {
                System.out.println(322);
                JOptionPane.showMessageDialog(this, errorMessage, "Update Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if ("updateSuccess".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, "Account successfully updated! Login Again", "Update Success", JOptionPane.INFORMATION_MESSAGE);
            viewModelManager.setActiveView(LoginView.viewName);
        }





    }

    private void prePopulate() {
        User currentUser = viewModelManager.getCurrentUser();
        if (currentUser != null) {
            currentUsernameField.setText(currentUser.getUsername() != null ? currentUser.getUsername() : "");
            bioField.setText(currentUser.getBio() != null ? currentUser.getBio() : "");
            ageField.setText(currentUser.getAge() != null ? currentUser.getAge().toString() : "");
            programOfStudyField.setText(currentUser.getProgramOfStudy() != null ? currentUser.getProgramOfStudy() : "");

            // Assuming the interests are stored as a list of strings
            if (currentUser.getInterests() != null) {
                if (!currentUser.getInterests().isEmpty()) {
                    interest1Field.setText(currentUser.getInterests().get(0) != null ? currentUser.getInterests().get(0) : "");
                }
                if (currentUser.getInterests().size() > 1) {
                    interest2Field.setText(currentUser.getInterests().get(1) != null ? currentUser.getInterests().get(1) : "");
                }
                if (currentUser.getInterests().size() > 2) {
                    interest3Field.setText(currentUser.getInterests().get(2) != null ? currentUser.getInterests().get(2) : "");
                }
            }
        } else {
            // Handle case where currentUser is null
            System.out.println("Current user is null.");
        }
    }
}


