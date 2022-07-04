package View;

import Model.UserAccount;
import Services.UserService;
import Utility.UtilityClass;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountForm extends JFrame{
    private JTextField txtEmail;
    private JButton btnCreateAccount;
    private JLabel lblEmail;
    private JPanel CreateAccountPanel;
    private JLabel lblPassword;
    private JPasswordField txtPassword;

    public CreateAccountForm(){
        setTitle("Create Account");
        setSize(400, 300);
        setContentPane(CreateAccountPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        setLocation(UtilityClass.getCenterXPosition(getSize()),UtilityClass.getCenterYPosition(getSize()));

        Border margin = new EmptyBorder(10, 10, 10, 10);
        lblEmail.setBorder(margin);
        txtEmail.setBorder(margin);
        lblPassword.setBorder(margin);
        txtPassword.setBorder(margin);
        btnCreateAccount.setBorder(margin);

        lblEmail.setText("Email Address");
        lblPassword.setText("Password");
        btnCreateAccount.setText("Create Account");

        btnCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());

                if (!UtilityClass.validateUserAndPassword(email,password)){
                    JOptionPane.showMessageDialog(null, "Error: Please input the correct email address and password should be more than 6 characters");
                    return;
                }

                UserService userService = new UserService();
                UserAccount userAccount = new UserAccount();
                userAccount = userService.createAccount(email,password);

                if (userAccount.getIdToken() != null)
                    dispose();
            }
        });
    }
}
