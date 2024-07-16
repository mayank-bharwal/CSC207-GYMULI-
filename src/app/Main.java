
package app;

import entity.MessageFactory;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.account_creation.SignUpViewModel;


import interface_adapter.ViewModelManager;
import use_case.account_creation.AccountCreationInputBoundary;
import use_case.account_creation.AccountCreationInteractor;
import use_case.account_creation.AccountCreationOutputBoundary;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.account_creation.SignUpPresenter;
import DataAccess.MongoDB.UserDataAccessObject;
import views.SignupView;
import views.LoginView;
import views.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

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
        SignUpViewModel signupViewModel = new SignUpViewModel();

        UserFactory userFactory = new CommonUserFactory();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory, new HashMap<>(), new HashMap<>(), new MessageFactory());

        AccountCreationOutputBoundary accountCreationOutputBoundary = new SignUpPresenter(viewModelManager, signupViewModel);

        AccountCreationInputBoundary accountCreationInputBoundary = new AccountCreationInteractor(userDataAccessObject, accountCreationOutputBoundary, userFactory);

        SignupView signupView = SignupViewFactory.create(viewModelManager, loginViewModel, signupViewModel, accountCreationInputBoundary);
        views.add(signupView, signupView.viewName);

        LoginView loginView = new LoginView(loginViewModel, viewModelManager);
        views.add(loginView, loginView.viewName);

        viewModelManager.setActiveView(loginView.viewName);
        viewModelManager.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
