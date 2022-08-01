package Model;

import com.google.gson.annotations.SerializedName;

public class UserAccount {
    @SerializedName("displayName")
    private String name;

    @SerializedName("kind")
    private String kindToken;

    @SerializedName("localId")
    private String localIdToken;

    @SerializedName("email")
    private String emailAddress;

    @SerializedName("registered")
    private boolean registered;

    @SerializedName("idToken")
    private String idToken;

    @SerializedName("refreshToken")
    private String refreshToken;

    private boolean isEmailVerified;
    private boolean isLogin;
    private double money;

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", kindToken='" + kindToken + '\'' +
                ", localIdToken='" + localIdToken + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", registered=" + registered +
                ", idToken='" + idToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", isEmailVerified=" + isEmailVerified +
                ", isLogin=" + isLogin +
                ", money=" + money +
                '}';
    }

    public String getIdToken() {
        return idToken;
    }

    public UserAccount() {
        this.name = null;
        this.kindToken = null;
        this.localIdToken = null;
        this.emailAddress = null;
        this.registered = false;
        this.idToken = null;
        this.refreshToken = null;
        this.isEmailVerified = false;
        this.isLogin = false;
        this.money = 0.0;
    }

    public UserAccount(String emailAddress) {
        this.emailAddress = emailAddress;
        this.money = 100000;
        this.name = null;
        this.kindToken = null;
        this.localIdToken = null;
        this.registered = false;
        this.idToken = null;
        this.refreshToken = null;
        this.isEmailVerified = false;
        this.isLogin = false;
    }

    public UserAccount(String name, String kindToken, String localIdToken, String emailAddress, boolean registered, String idToken, String refreshToken) {
        this.name = name;
        this.kindToken = kindToken;
        this.localIdToken = localIdToken;
        this.emailAddress = emailAddress;
        this.registered = registered;
        this.idToken = idToken;
        this.refreshToken = refreshToken;
        this.isEmailVerified = false;
        this.isLogin = false;
        this.money = 100000;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public boolean isLogin(){
        return isLogin;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.isEmailVerified = emailVerified;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public double getMoney() {
        return money;
    }

}
