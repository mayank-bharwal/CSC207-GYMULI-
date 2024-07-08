package use_case.update_login_credentials;

public class UpdateLoginCredentialsInputData {
    private String newUsername;
    private String password;
    private String newPassword;
    private String rePassword;

    public UpdateLoginCredentialsInputData(String username, String password, String newPassword, String rePassword, String newUsername) {
        this.password = password;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.rePassword = rePassword;
    }



    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public String getNewUsername() {
        return newUsername;
    }

}
