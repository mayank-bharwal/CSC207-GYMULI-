package views;

import interface_adapter.ViewModelManager;
import interface_adapter.account_creation.SignupController;
import interface_adapter.account_creation.SignupViewModel;
import interface_adapter.update_profile.UpdateProfileController;
import interface_adapter.update_profile.UpdateProfilePresenter;
import interface_adapter.update_profile.UpdateProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UpdateProfileView  extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "UpdateProfileView";

    private final UpdateProfileViewModel updateProfileViewModel;
    private final UpdateProfileController updateProfileController;
    private final ViewModelManager viewModelManager;

    //private final JButton Update;
    //private final JButton Clear;
    //private final JButton Back;

    public UpdateProfileView(UpdateProfileViewModel updateProfileViewModel, UpdateProfileController updateProfileController,
                             ViewModelManager viewModelManager){
        this.updateProfileViewModel = updateProfileViewModel;
        this.updateProfileController = updateProfileController;
        this.viewModelManager = viewModelManager;
        updateProfileViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel formPanel = new JPanel(new GridLayout(12, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
