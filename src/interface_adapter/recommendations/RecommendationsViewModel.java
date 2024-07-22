package interface_adapter.recommendations;

import interface_adapter.ViewModel;
import interface_adapter.search_user.SearchUserState;

import javax.swing.*;
import java.beans.PropertyChangeSupport;

public class RecommendationsViewModel extends ViewModel {

    public final String RECOMMENDATIONS_TITLE = "recommendations view";
    public final String RECOMMENDATIONS_DETAILS = "Find Friends";

    public final String RECOMMENDATION_BUTTON_LABEL = "Find Friends";
    public final String NO_OF_USERS_LABEL = "How Many Similar Users";

    public final String GO_BACK_BUTTON_LABEL = "Go Back";

    private RecommendationsState state = new RecommendationsState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public final JTextField noofuserInputField = new JTextField(5);

    public final JButton findFriendsButton = new JButton(RECOMMENDATION_BUTTON_LABEL);
    public final JButton goBackButton = new JButton(GO_BACK_BUTTON_LABEL);

    public RecommendationsState getState() {return state;}

    public void setState(RecommendationsState state) {this.state = state;}

}
