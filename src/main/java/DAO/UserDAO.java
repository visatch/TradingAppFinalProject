package DAO;

import Model.UserAccount;

public interface UserDAO {
    public UserAccount signIn(final String emailInput, final String password);
    public boolean resetPassword(final String email);
    public UserAccount createAccount(final String email, final String password);
    public UserAccount getUserInfo(final String idToken, UserAccount userAccount);

    public boolean isUserEmailVerified(final String idToken, UserAccount userAccount);
}
