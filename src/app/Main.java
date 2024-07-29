package app;

import data_access.*;
import data_access.readDB.*;
import entity.*;
import interface_adapter.*;
import interface_adapter.Login.*;
import interface_adapter.account_creation.*;
import interface_adapter.make_chat.*;
import interface_adapter.retrieve_chat.*;
import interface_adapter.send_message.*;
import interface_adapter.update_profile.*;
import use_case.account_creation.*;
import use_case.login.*;
import use_case.make_chat.*;
import use_case.retrieve_chat.*;
import use_case.send_message.*;
import use_case.update_profile.*;
import views.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Main application class for GYMULI.
 */

public class Main {

    /**
     * Entry point of the application.
     *
     * @param args Command line arguments.
     */

    public static void main(String[] args) {
        new Main().startApplication();
    }

    private void startApplication() {
        JFrame application = createApplicationFrame();
        ViewModelManager viewModelManager = new ViewModelManager();
        JPanel views = initializeViews(application, viewModelManager);
        MongoConnection mongoConnection = new MongoConnection();
        UserFactory userFactory = new CommonUserFactory();
        UserDataAccessObject userDataAccessObject = createUserDAO(userFactory, mongoConnection);
        ChatDataAccessObject chatDataAccessObject = createChatDAO(mongoConnection, userDataAccessObject);

        setupViews(viewModelManager, views, userDataAccessObject, chatDataAccessObject, userFactory);

        application.pack();
        application.setVisible(true);
    }

    private JFrame createApplicationFrame() {
        JFrame application = new JFrame("GYMULI");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setPreferredSize(new Dimension(800, 600));
        return application;
    }

    private JPanel initializeViews(JFrame application, ViewModelManager viewModelManager) {
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        new ViewManager(views, cardLayout, viewModelManager);
        return views;
    }

    private UserDataAccessObject createUserDAO(UserFactory userFactory, MongoConnection mongoConnection) {
        return new UserDataAccessObject(userFactory, new HashMap<>(), mongoConnection);
    }

    private ChatDataAccessObject createChatDAO(MongoConnection mongoConnection, UserDataAccessObject userDataAccessObject) {
        return new ChatDataAccessObject(mongoConnection, new HashMap<>(), new MessageFactory(), new HashMap<>(), new ChatFactory(), userDataAccessObject);
    }

    private void setupViews(ViewModelManager viewModelManager, JPanel views, UserDataAccessObject userDataAccessObject, ChatDataAccessObject chatDataAccessObject, UserFactory userFactory) {
        setupLogin(viewModelManager, views, userDataAccessObject);
        setupSignup(viewModelManager, views, userDataAccessObject, userFactory);
        setupMain(viewModelManager, views, chatDataAccessObject);
        setupChat(viewModelManager, views, chatDataAccessObject);
        setupCreateChat(viewModelManager, views, chatDataAccessObject);
        setupUpdateProfile(viewModelManager, views, userDataAccessObject);

        viewModelManager.setActiveView(LoginView.viewName);
        viewModelManager.firePropertyChanged();
    }

    private void setupLogin(ViewModelManager viewModelManager, JPanel views, UserDataAccessObject userDataAccessObject) {
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginViewModel, viewModelManager);
        LoginInputBoundary loginInputBoundary = new LoginInteractor(loginOutputBoundary, userDataAccessObject);
        LoginView loginView = LoginViewFactory.create(viewModelManager, loginViewModel, loginInputBoundary);
        views.add(loginView, loginView.viewName);
    }

    private void setupSignup(ViewModelManager viewModelManager, JPanel views, UserDataAccessObject userDataAccessObject, UserFactory userFactory) {
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AccountCreationOutputBoundary accountCreationOutputBoundary = new SignupPresenter(viewModelManager, signupViewModel, loginViewModel);
        AccountCreationInputBoundary accountCreationInputBoundary = new AccountCreationInteractor(userDataAccessObject, accountCreationOutputBoundary, userFactory);
        SignupView signupView = SignupViewFactory.create(viewModelManager, loginViewModel, signupViewModel, accountCreationInputBoundary);
        views.add(signupView, signupView.viewName);
    }

    private void setupMain(ViewModelManager viewModelManager, JPanel views, ChatDataAccessObject chatDataAccessObject) {
        RetrieveChatViewModel retrieveChatViewModel = new RetrieveChatViewModel();
        RetrieveChatPresenter retrieveChatPresenter = new RetrieveChatPresenter(retrieveChatViewModel);
        RetrieveChatInputBoundary retrieveChatInteractor = new RetrieveChatInteractor(retrieveChatPresenter, chatDataAccessObject);
        RetrieveChatController retrieveChatController = new RetrieveChatController(retrieveChatInteractor);
        retrieveChatViewModel.setRetrieveChatInteractor(retrieveChatInteractor);
        MainView mainView = MainViewFactory.create(viewModelManager, retrieveChatController);
        views.add(mainView, MainView.viewName);
    }

    private void setupChat(ViewModelManager viewModelManager, JPanel views, ChatDataAccessObject chatDataAccessObject) {
        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
        SendMessagePresenter sendMessagePresenter = new SendMessagePresenter(sendMessageViewModel);
        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(chatDataAccessObject, sendMessagePresenter, new MessageFactory());
        SendMessageController sendMessageController = new SendMessageController(sendMessageInteractor);
        ChatView chatView = ChatViewFactory.create(viewModelManager, sendMessageController, sendMessageViewModel, new RetrieveChatViewModel());
        views.add(chatView, ChatView.viewName);
    }

    private void setupCreateChat(ViewModelManager viewModelManager, JPanel views, ChatDataAccessObject chatDataAccessObject) {
        CreateChatViewModel createChatViewModel = new CreateChatViewModel();
        CreateChatPresenter createChatPresenter = new CreateChatPresenter(createChatViewModel);
        MakeChatInputBoundary makeChatInputBoundary = new MakeChatInteractor(chatDataAccessObject, createChatPresenter, new ChatFactory());
        CreateChatView createChatView = CreateChatViewFactory.create(viewModelManager, createChatViewModel, makeChatInputBoundary);
        views.add(createChatView, CreateChatView.viewName);
    }

    private void setupUpdateProfile(ViewModelManager viewModelManager, JPanel views, UserDataAccessObject userDataAccessObject) {
        UpdateProfileViewModel updateProfileViewModel = new UpdateProfileViewModel();
        UpdateProfileOutputBoundary updateProfilePresenter = new UpdateProfilePresenter(updateProfileViewModel, viewModelManager);
        UpdateProfileInputBoundary updateProfileInputBoundary = new UpdateProfileInteractor(userDataAccessObject, updateProfilePresenter);
        UpdateProfileView updateProfileView = UpdateProfileViewFactory.create(viewModelManager, updateProfileViewModel, updateProfileInputBoundary);
        views.add(updateProfileView, UpdateProfileView.viewName);
    }
}

