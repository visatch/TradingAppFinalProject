package Services;

import DAO.UserDAO;
import Model.UserAccount;
import Utility.ApplicationProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.swing.*;

public class UserService implements UserDAO {
    public UserAccount signIn(final String emailInput, final String password)
    {
        UserAccount userAccount = new UserAccount();
        try {
            HttpResponse<JsonNode> response = Unirest.post(ApplicationProperties.firebase_Signin)
                .header("accept","application/json")
                .queryString("key", ApplicationProperties.apiKey)
                .queryString("email",emailInput)
                .queryString("password",password)
                .queryString("returnSecureToken",true)
                .asJson();
            if (response.getStatus() == 200)
            {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                userAccount = gson.fromJson(response.getBody().toString(), UserAccount.class);
                getUserInfo(userAccount.getIdToken(),userAccount);
                if (userAccount.isEmailVerified()){
                    JOptionPane.showMessageDialog(null,"The user is logged in and verified email.");
                }else {
                    JOptionPane.showMessageDialog(null,"The user is logged in but not yet verified email");
                }
            } else
                JOptionPane.showMessageDialog(null,"The email or password is wrong");

        } catch (UnirestException ex) {
            JOptionPane.showMessageDialog(null,"There is an error with API calling to firebase");
        }
        return userAccount;
    }

    @Override
    public boolean resetPassword(final String email) {
        try{
            HttpResponse<JsonNode> response = Unirest.post(ApplicationProperties.firebase_SendOOB)
                    .header("accept","application/json")
                    .queryString("key",ApplicationProperties.apiKey)
                    .queryString("requestType","PASSWORD_RESET")
                    .queryString("email",email)
                    .asJson();
            if (response.getStatus() == 200){
                JOptionPane.showMessageDialog(null,"The link to reset password has sent to your email address. Please check your inbox!");
                return true;
            } else {
                JsonObject jsonObject = new Gson().fromJson(response.getBody().toString(),JsonObject.class);
                JOptionPane.showMessageDialog(null,jsonObject.getAsJsonObject("error").
                        getAsJsonArray("errors").get(0).getAsJsonObject().get("message").getAsString().replace('\"',' '));
            }
        }catch (UnirestException ex){
            JOptionPane.showMessageDialog(null,"There is an error with API calling to firebase");
        }
        return false;
    }

    @Override
    public UserAccount getUserInfo(final String idToken, UserAccount userAccount) {
        try{
            HttpResponse<JsonNode> response = Unirest.post(ApplicationProperties.firebase_LookUp)
                    .header("accept","application/json")
                    .queryString("key",ApplicationProperties.apiKey)
                    .queryString("idToken",idToken)
                    .asJson();
            if (response.getStatus() == 200){
                JsonObject jsonObject = new Gson().fromJson(response.getBody().toString(),JsonObject.class);
                if (jsonObject.getAsJsonArray("users").get(0).getAsJsonObject().get("emailVerified").getAsBoolean()){
                    userAccount.setEmailVerified(true);
                }
            }
        }catch (UnirestException ex){
            JOptionPane.showMessageDialog(null,"Error in calling the api " + ": " + ex.toString());
        }
        return userAccount;
    }

    @Override
    public boolean isUserEmailVerified(String idToken, UserAccount userAccount) {
        try{
            HttpResponse<JsonNode> response = Unirest.post(ApplicationProperties.firebase_LookUp)
                    .header("accept","application/json")
                    .queryString("key",ApplicationProperties.apiKey)
                    .queryString("idToken",idToken)
                    .asJson();
            if (response.getStatus() == 200){
                JsonObject jsonObject = new Gson().fromJson(response.getBody().toString(),JsonObject.class);
                if (jsonObject.getAsJsonArray("users").get(0).getAsJsonObject().get("emailVerified").getAsBoolean()){
                    userAccount.setEmailVerified(true);
                    return true;
                }
            }
        }catch (UnirestException ex){
            JOptionPane.showMessageDialog(null,"Error in calling the api " + ": " + ex.toString());

        }
        return false;
    }


    @Override
    public UserAccount createAccount(final String email, final String password) {
        UserAccount userAccount = new UserAccount();
        try{
            HttpResponse<JsonNode> response = Unirest.post(ApplicationProperties.firebase_Signup)
                    .header("accept","application/json")
                    .queryString("key",ApplicationProperties.apiKey)
                    .queryString("email",email)
                    .queryString("password",password)
                    .queryString("returnSecureToken",true)
                    .asJson();
            if (response.getStatus() == 200) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                userAccount = gson.fromJson(response.getBody().toString(), UserAccount.class);
                JOptionPane.showMessageDialog(null,"The user with email " + email + " is created. Please confirm your email in your mailbox before you can login.");
            } else {
                JsonObject jsonObject = new Gson().fromJson(response.getBody().toString(), JsonObject.class);
                JOptionPane.showMessageDialog(null,"Error: " + jsonObject.getAsJsonObject("error").getAsJsonObject().get("message").getAsString().replace('\"',' '));
            }

        }catch (UnirestException ex){
            JOptionPane.showMessageDialog(null,"There is an error with API calling to firebase");
        }
        return userAccount;
    }



}
