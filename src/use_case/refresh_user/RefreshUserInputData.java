package use_case.refresh_user;

public class RefreshUserInputData {

    final private String User;


    public RefreshUserInputData(String user) {
        User = user;
    }

    public String getUser() {
        return User;
    }
}
