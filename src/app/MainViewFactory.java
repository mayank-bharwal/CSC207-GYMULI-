package app;

import interface_adapter.ViewModelManager;
import interface_adapter.retrieve_chat.RetrieveChatController;
import views.MainView;

public class MainViewFactory {
    public static MainView create(ViewModelManager viewModelManager, RetrieveChatController retrieveChatController) {
        return new MainView(viewModelManager, retrieveChatController);
    }
}
