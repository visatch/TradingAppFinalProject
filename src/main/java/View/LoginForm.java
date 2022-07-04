package View;

import Model.UserAccount;
import Services.UserService;
import Utility.UtilityClass;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField txtEmail;
    private JButton btnLogin;
    private JPasswordField txtPassword;
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JPanel panelLogin;
    private JButton btnResetPassword;
    private JButton btnCreateAccount;

    public LoginForm(){
        setContentPane(panelLogin);
        setSize(400,300);
        setTitle("Trading App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLocation(UtilityClass.getCenterXPosition(getSize()),UtilityClass.getCenterYPosition(getSize()));

        Border margin = new EmptyBorder(10, 10, 10, 10);
        lblEmail.setBorder(margin);
        lblPassword.setBorder(margin);
        btnLogin.setBorder(margin);
        txtEmail.setBorder(margin);
        txtPassword.setBorder(margin);
        btnResetPassword.setBorder(margin);
        btnCreateAccount.setBorder(margin);

        lblEmail.setText("Email Address");
        lblPassword.setText("Password");
        btnLogin.setText("Login");
        btnResetPassword.setText("Reset Password");
        btnCreateAccount.setText("Create Account");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogin.setEnabled(false);
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());
                UserService userService = new UserService();

                UserAccount userAccount = userService.signIn(email, password); //To be continue
//                if (userAccount.getIdToken() != null){
//                    if (userService.isUserEmailVerified(userAccount.getIdToken(),userAccount)){
//                        JOptionPane.showMessageDialog(null,"Logged in Success");
//                    } else {
//                        JOptionPane.showMessageDialog(null,"Account is registered, but not yet verified");
//                    }
//                }

                btnLogin.setEnabled(true);
            }
        });

        btnResetPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
            }
        });

        btnCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccountForm createAccountForm = new CreateAccountForm();
            }
        });


    }
}
