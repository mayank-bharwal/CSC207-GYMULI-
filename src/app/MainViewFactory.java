package app;

import interface_adapter.ViewModelManager;
import views.MainView;

public class MainViewFactory {
    public static MainView create(ViewModelManager viewModelManager) {
        return new MainView(viewModelManager);
    }
}
