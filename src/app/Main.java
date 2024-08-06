package app;

import data_access.ChatDataAccessObject;
import data_access.readDB.MongoConnection;
import entity.ChatFactory;
import entity.MessageFactory;
import interface_adapter.Login.LoginPresenter;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.account_creation.SignupViewModel;
import interface_adapter.ViewModelManager;
import interface_adapter.add_friends.AddFriendsController;
import interface_adapter.add_friends.AddFriendsPresenter;
import interface_adapter.add_friends.AddFriendsViewModel;
import interface_adapter.make_chat.CreateChatPresenter;
import interface_adapter.make_chat.CreateChatViewModel;
import interface_adapter.refresh_user.RefreshUserController;
import interface_adapter.refresh_user.RefreshUserPresenter;
import interface_adapter.refresh_user.RefreshUserViewModel;
import interface_adapter.remove_friends.RemoveFriendsController;
import interface_adapter.remove_friends.RemoveFriendsPresenter;
import interface_adapter.remove_friends.RemoveFriendsViewModel;
import interface_adapter.recommendations.RecommendationsController;
import interface_adapter.recommendations.RecommendationsPresenter;
import interface_adapter.recommendations.RecommendationsViewModel;
import interface_adapter.retrieve_chat.RetrieveChatController;
import interface_adapter.retrieve_chat.RetrieveChatPresenter;
import interface_adapter.retrieve_chat.RetrieveChatViewModel;
import interface_adapter.search_user.SearchUserController;
import interface_adapter.search_user.SearchUserPresenter;
import interface_adapter.search_user.SearchUserViewModel;
import interface_adapter.update_profile.UpdateProfilePresenter;
import interface_adapter.update_profile.UpdateProfileViewModel;
import use_case.account_creation.AccountCreationInputBoundary;
import use_case.account_creation.AccountCreationInteractor;
import use_case.account_creation.AccountCreationOutputBoundary;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.account_creation.SignupPresenter;
import data_access.UserDataAccessObject;
import use_case.add_friends.AddFriendsInputBoundary;
import use_case.add_friends.AddFriendsInteractor;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.make_chat.MakeChatInputBoundary;
import use_case.make_chat.MakeChatInteractor;
import use_case.refresh_user.RefreshUserInputBoundary;
import use_case.refresh_user.RefreshUserInteractor;
import use_case.refresh_user.RefreshUserOutputBoundary;
import use_case.remove_friends.RemoveFriendsInputBoundary;
import use_case.remove_friends.RemoveFriendsInteractor;
import use_case.recommendations.RecommendationsInputBoundary;
import use_case.recommendations.RecommendationsInputData;
import use_case.recommendations.RecommendationsInteractor;
import use_case.recommendations.RecommendationsOutputBoundary;
import use_case.retrieve_chat.RetrieveChatInputBoundary;
import use_case.retrieve_chat.RetrieveChatInteractor;
import use_case.search_user.SearchUserDataAccessInterface;
import use_case.search_user.SearchUserInputBoundary;
import use_case.search_user.SearchUserInteractor;
import use_case.search_user.SearchUserOutputBoundary;
import use_case.send_message.SendMessageInteractor;
import use_case.update_profile.UpdateProfileInputBoundary;
import use_case.update_profile.UpdateProfileInteractor;
import use_case.update_profile.UpdateProfileOutputBoundary;
import views.*;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.send_message.SendMessageViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

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
        UpdateProfileViewModel updateProfileViewModel = new UpdateProfileViewModel();
        RecommendationsViewModel recommendationsViewModel = new RecommendationsViewModel();
        RefreshUserViewModel refreshUserViewModel = new RefreshUserViewModel();

        MongoConnection mongoConnection = new MongoConnection();

        UserFactory userFactory = new CommonUserFactory();
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject(userFactory, new HashMap<>(), mongoConnection);
        ChatDataAccessObject chatDataAccessObject = new ChatDataAccessObject(mongoConnection, new HashMap<>(), new MessageFactory(), new HashMap<>(), new ChatFactory(), userDataAccessObject);


        AccountCreationOutputBoundary accountCreationOutputBoundary = new SignupPresenter(viewModelManager, signupViewModel, loginViewModel);
        AccountCreationInputBoundary accountCreationInputBoundary = new AccountCreationInteractor(userDataAccessObject, accountCreationOutputBoundary, userFactory);

        SignupView signupView = SignupViewFactory.create(viewModelManager, loginViewModel, signupViewModel, accountCreationInputBoundary);
        views.add(signupView, signupView.viewName);

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(loginViewModel, viewModelManager);
        LoginInputBoundary loginInputBoundary = new LoginInteractor(loginOutputBoundary, userDataAccessObject);

        LoginView loginView = LoginViewFactory.create(viewModelManager, loginViewModel, loginInputBoundary);
        views.add(loginView, loginView.viewName);

        RetrieveChatViewModel retrieveChatViewModel = new RetrieveChatViewModel();
        RetrieveChatPresenter retrieveChatPresenter = new RetrieveChatPresenter(retrieveChatViewModel);
        RetrieveChatInputBoundary retrieveChatInteractor = new RetrieveChatInteractor(retrieveChatPresenter, chatDataAccessObject);
        RetrieveChatController retrieveChatController = new RetrieveChatController(retrieveChatInteractor);
        retrieveChatViewModel.setRetrieveChatInteractor(retrieveChatInteractor);

        MainView mainView = MainViewFactory.create(viewModelManager, retrieveChatController, userDataAccessObject);
        views.add(mainView, MainView.viewName);

        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
        SendMessagePresenter sendMessagePresenter = new SendMessagePresenter(sendMessageViewModel);
        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(chatDataAccessObject, sendMessagePresenter, new MessageFactory());
        SendMessageController sendMessageController = new SendMessageController(sendMessageInteractor);

        ChatView chatView = ChatViewFactory.create(viewModelManager, sendMessageController, sendMessageViewModel, retrieveChatViewModel);
        views.add(chatView, ChatView.viewName);

        CreateChatPresenter createChatPresenter = new CreateChatPresenter(createChatViewModel);
        MakeChatInputBoundary makeChatInputBoundary = new MakeChatInteractor(chatDataAccessObject, createChatPresenter, new ChatFactory());
        CreateChatView createChatView = CreateChatViewFactory.create(viewModelManager, createChatViewModel, makeChatInputBoundary);
        views.add(createChatView, CreateChatView.viewName);

        UpdateProfileOutputBoundary updateProfilePresenter = new UpdateProfilePresenter(updateProfileViewModel, viewModelManager);
        UpdateProfileInputBoundary updateProfileInputBoundary = new UpdateProfileInteractor(userDataAccessObject, updateProfilePresenter);
        UpdateProfileView updateProfileView = UpdateProfileViewFactory.create(viewModelManager, updateProfileViewModel, updateProfileInputBoundary);
        views.add(updateProfileView, UpdateProfileView.viewName);


        AddFriendsViewModel addFriendsViewModel = new AddFriendsViewModel();
        AddFriendsPresenter addFriendsPresenter = new AddFriendsPresenter(addFriendsViewModel);
        AddFriendsInputBoundary addFriendsInteractor = new AddFriendsInteractor(addFriendsPresenter, userDataAccessObject);
        AddFriendsController addFriendsController = new AddFriendsController(addFriendsInteractor);

        RefreshUserOutputBoundary refreshUserPresenter = new RefreshUserPresenter(refreshUserViewModel, viewModelManager);
        RefreshUserInputBoundary refreshUserInteractor = new RefreshUserInteractor(userDataAccessObject, refreshUserPresenter);
        RefreshUserController refreshUserController = new RefreshUserController(refreshUserInteractor);

        RemoveFriendsViewModel removeFriendsViewModel = new RemoveFriendsViewModel();
        RemoveFriendsPresenter removeFriendsPresenter = new RemoveFriendsPresenter(removeFriendsViewModel);
        RemoveFriendsInputBoundary removeFriendsInteractor = new RemoveFriendsInteractor(userDataAccessObject, removeFriendsPresenter);
        RemoveFriendsController removeFriendsController = new RemoveFriendsController(removeFriendsInteractor, viewModelManager);

        EditFriendsView friendsView = FriendsViewFactory.create(viewModelManager, addFriendsViewModel,removeFriendsViewModel, removeFriendsController, addFriendsController, refreshUserController);
        views.add(friendsView, EditFriendsView.viewName);

        SearchUserViewModel searchUserViewModel = new SearchUserViewModel();
        SearchUserOutputBoundary searchUserOutputBoundary = new SearchUserPresenter(searchUserViewModel);
        SearchUserInputBoundary searchUserInputBoundary = new SearchUserInteractor(searchUserOutputBoundary, userDataAccessObject);
        SearchUserController searchUserController = new SearchUserController(searchUserInputBoundary);

        RecommendationsOutputBoundary recommendationsOutputBoundary = new RecommendationsPresenter(recommendationsViewModel, viewModelManager);
        RecommendationsInputBoundary recommendationsInputBoundary = new RecommendationsInteractor(userDataAccessObject, recommendationsOutputBoundary);
        RecommendationsController recommendationsController = new RecommendationsController(recommendationsInputBoundary);
        RecommendationView recommendationView = RecommendationViewFactory.create(viewModelManager, recommendationsController, recommendationsViewModel, searchUserController, searchUserViewModel);
        views.add(recommendationView, RecommendationView.viewName);

        ProfileView profileView = ProfileViewFactory.create(viewModelManager);
        views.add(profileView, ProfileView.viewName);

        viewModelManager.setActiveView(loginView.viewName);
        viewModelManager.firePropertyChanged();

        userDataAccessObject.startTimer();

        application.pack();
        application.setVisible(true);
    }


}
