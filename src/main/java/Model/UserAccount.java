package Model;

import com.google.gson.annotations.SerializedName;

public class UserAccount {
    @SerializedName("displayName")
    String name;

    @SerializedName("kind")
    String kindToken;

    @SerializedName("localId")
    String localIdToken;

    @SerializedName("email")
    String emailAddress;

    @SerializedName("registered")
    boolean registered;

    @SerializedName("idToken")
    String idToken;

    @SerializedName("refreshToken")
    String refreshToken;

    boolean emailVerified;

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
                ", emailVerified=" + emailVerified +
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
        this.emailVerified = false;
    }

    public UserAccount(String name, String kindToken, String localIdToken, String emailAddress, boolean registered, String idToken, String refreshToken) {
        this.name = name;
        this.kindToken = kindToken;
        this.localIdToken = localIdToken;
        this.emailAddress = emailAddress;
        this.registered = registered;
        this.idToken = idToken;
        this.refreshToken = refreshToken;
        this.emailVerified = false;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
}
