package DAO;

import Model.UserAccount;

public interface UserDAO {
    public UserAccount signIn(final String emailInput, final String password);
    public boolean resetPassword(final String email);
    public UserAccount createAccount(final String email, final String password);
    public void getUserInfo(final String idToken, UserAccount userAccount);
    public void sendConfirmationEmail(UserAccount userAccount);

    public boolean isUserEmailVerified(final String idToken, UserAccount userAccount);
}
