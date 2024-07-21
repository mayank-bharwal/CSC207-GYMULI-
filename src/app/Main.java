package app;

import data_access.ChatDataAccessObject;
import data_access.MongoConnection;
import entity.ChatFactory;
import entity.MessageFactory;
import interface_adapter.Login.LoginPresenter;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.account_creation.SignupViewModel;
import interface_adapter.ViewModelManager;
import interface_adapter.make_chat.CreateChatPresenter;
import interface_adapter.make_chat.CreateChatViewModel;
import interface_adapter.retrieve_chat.RetrieveChatController;
import interface_adapter.retrieve_chat.RetrieveChatPresenter;
import interface_adapter.retrieve_chat.RetrieveChatViewModel;
import use_case.account_creation.AccountCreationInputBoundary;
import use_case.account_creation.AccountCreationInteractor;
import use_case.account_creation.AccountCreationOutputBoundary;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.account_creation.SignupPresenter;
import data_access.UserDataAccessObject;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.make_chat.MakeChatInputBoundary;
import use_case.make_chat.MakeChatInteractor;
import use_case.retrieve_chat.RetrieveChatInputBoundary;
import use_case.retrieve_chat.RetrieveChatInteractor;
import use_case.retrieve_chat.RetrieveChatOutputBoundary;
import use_case.retrieve_chat.RetrieveChatUserDataAccessInterface;
import views.MainView;
import views.LoginView;
import views.SignupView;
import views.ChatView;
import views.CreateChatView;
import views.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("GYMULI");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setPreferredSize(new Dimension(800, 600));

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewModelManager viewModelManager = new ViewModelManager();
        new ViewManager(views, cardLayout, viewModelManager);

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        CreateChatViewModel createChatViewModel = new CreateChatViewModel();
        RetrieveChatViewModel retrieveChatViewModel = new RetrieveChatViewModel();

        MongoConnection mongoConnection = new MongoConnection();

        UserFactory userFactory = new CommonUserFactory();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory, new HashMap<>(), mongoConnection);
        ChatDataAccessObject chatDataAccessObject = new ChatDataAccessObject(new MongoConnection(), new HashMap<>(), new MessageFactory(), new HashMap<>(), new ChatFactory(), userDataAccessObject);

        AccountCreationOutputBoundary accountCreationOutputBoundary = new SignupPresenter(viewModelManager, signupViewModel, loginViewModel);
        AccountCreationInputBoundary accountCreationInputBoundary = new AccountCreationInteractor(userDataAccessObject, accountCreationOutputBoundary, userFactory);

        SignupView signupView = SignupViewFactory.create(viewModelManager, loginViewModel, signupViewModel, accountCreationInputBoundary);
        views.add(signupView, signupView.viewName);

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginViewModel, viewModelManager);
        LoginInputBoundary loginInputBoundary = new LoginInteractor(loginOutputBoundary, userDataAccessObject);

        LoginView loginView = LoginViewFactory.create(viewModelManager, loginViewModel, loginInputBoundary);
        views.add(loginView, loginView.viewName);

        RetrieveChatOutputBoundary retrieveChatPresenter = new RetrieveChatPresenter(retrieveChatViewModel);
        RetrieveChatInputBoundary retrieveChatInteractor = new RetrieveChatInteractor(retrieveChatPresenter, chatDataAccessObject);
        RetrieveChatController retrieveChatController = new RetrieveChatController(retrieveChatInteractor);

        MainView mainView = MainViewFactory.create(viewModelManager, retrieveChatController);
        views.add(mainView, MainView.viewName);

        ChatView chatView = ChatViewFactory.create(viewModelManager, retrieveChatViewModel);
        views.add(chatView, ChatView.viewName);

        CreateChatPresenter createChatPresenter = new CreateChatPresenter(createChatViewModel);
        MakeChatInputBoundary makeChatInputBoundary = new MakeChatInteractor(chatDataAccessObject, createChatPresenter, new ChatFactory());
        CreateChatView createChatView = CreateChatViewFactory.create(viewModelManager, createChatViewModel, makeChatInputBoundary);
        views.add(createChatView, CreateChatView.viewName);

        viewModelManager.setActiveView(loginView.viewName);
        viewModelManager.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

