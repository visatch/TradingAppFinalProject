package View;

import Model.UserAccount;
import Services.UserService;
import Utility.UtilityClass;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Utility.UtilityClass.requestIPAndInsertIntoDB;

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
        requestIPAndInsertIntoDB();

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

        Action loginAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLogin.setEnabled(false);
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());
                UserService userService = new UserService();

                UserAccount userAccount = userService.signIn(email, password);

                if (userAccount.isLogin() && userAccount.isEmailVerified()){
                    dispose();
                    MainView mainView = new MainView(userAccount.getEmailAddress());

                }else if (userAccount.isLogin()){
                    if (!userAccount.isEmailVerified()){
                        int result = JOptionPane.showOptionDialog(null,"The account is not verified yet. Would you like to resent the confirmation email?",
                                "The email address is not verified yet",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] {"Resend","Cancel"},JOptionPane.NO_OPTION);
                        if (result == JOptionPane.YES_OPTION){
                            userService.sendConfirmationEmail(userAccount);
                        }
                    }
                }

                btnLogin.setEnabled(true);
            }
        };

        txtPassword.addActionListener(loginAction);

        btnLogin.addActionListener(loginAction);

        btnResetPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetPasswordForm resetPasswordForm = new ResetPasswordForm();
                txtEmail.setText(null);
                txtPassword.setText(null);
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
