package app;

import interface_adapter.ViewModelManager;
import views.ProfileView;

/**
 * A factory class for creating instances of {@link ProfileView}.
 */
public class ProfileViewFactory {

    /**
     * Creates a new {@link ProfileView} with the specified dependencies.
     *
     * @param viewModelManager the manager for view models, used to manage the state and logic of the views.
     * @return a new instance of {@link ProfileView}.
     */
    public static ProfileView create(ViewModelManager viewModelManager) {
        return new ProfileView(viewModelManager);
    }
}