package Utility;
import java.awt.*;

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
}
