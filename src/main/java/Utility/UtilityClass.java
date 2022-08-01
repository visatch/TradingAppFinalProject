package Utility;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static Utility.ApplicationProperties.*;

public final class UtilityClass {
    public static int getCenterXPosition(Dimension size){
        return ((Toolkit.getDefaultToolkit().getScreenSize().width) - size.width) / 2;
    }

    public static int getCenterYPosition(Dimension size){
        return ((Toolkit.getDefaultToolkit().getScreenSize().height) - size.height) / 2;
    }
//@gmail.com
    public static boolean validateUserAndPassword(String email, String password){
        return (email.contains("@") && password.length() >= 6);
    }
    public static String getUserIPAddress(){
        String ip = "";
        try{
            URL whatismyipaddress = new URL(whatismyAPIURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyipaddress.openStream()));
            ip = in.readLine();
            in.close();

        } catch (IOException e) {
            System.out.println(e.toString());;
        }
        return ip;
    }

    public static String getUserLocation(String ip){
        String result = "";
        try {
            URL url = new URL(apiGetUserLocation(ip));
            HttpResponse<JsonNode> response = Unirest.get(url.toString()).asJson();
            result += response.getBody().getObject().getString("city") + ", " + response.getBody().getObject().getString("country");

        } catch (IOException | UnirestException e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public static void requestIPAndInsertIntoDB(){
        try {
            Connection conn = DriverManager.getConnection(postgresDB, postgresUsername, postgresPW);
            Statement stmt = conn.createStatement();

            ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("America/Los_Angeles"));

            String ip = getUserIPAddress();
            String location = getUserLocation(ip);

            String queryString = "INSERT INTO \"tblIP\"(\"ipAddress\",\"Logged_Date\",\"location\") VALUES('" + ip + "','" + zonedDateTime.toLocalDateTime() + "','" + location + "')";
            int result = stmt.executeUpdate(queryString);

            if (result > 0){
//                System.out.println("ip logged");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());;
        }

    }
}
