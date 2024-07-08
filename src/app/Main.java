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

        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setPreferredSize(new Dimension(800, 600));

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewModelManager viewModelManager = new ViewModelManager();
        new ViewManager(views, cardLayout, viewModelManager);

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
