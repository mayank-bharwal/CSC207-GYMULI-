package app;

import interface_adapter.LoginViewModel;
import interface_adapter.SignupViewModel;
import interface_adapter.ViewModelManager;
import views.LoginView;
import views.SignupView;
import views.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setPreferredSize(new Dimension(800, 600));

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewModelManager viewModelManager = new ViewModelManager();
        new ViewManager(views, cardLayout, viewModelManager);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        SignupView signupView = SignupViewFactory.create(viewModelManager, loginViewModel, signupViewModel);
        views.add(signupView, signupView.viewName);

        LoginView loginView = new LoginView(loginViewModel, viewModelManager);
        views.add(loginView, loginView.viewName);

        viewModelManager.setActiveView(signupView.viewName);
        viewModelManager.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}