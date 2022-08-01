package View;

import Services.UserService;
import Utility.UtilityClass;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetPasswordForm extends JFrame {
    private JButton btnReset;
    private JTextField txtEmail;
    private JLabel lblEmail;
    private JPanel resetPasswordPanel;

    public ResetPasswordForm(){
        setContentPane(resetPasswordPanel);
        setSize(400,200);
        setTitle("Reset Password");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        setLocation(UtilityClass.getCenterXPosition(getSize()),UtilityClass.getCenterYPosition(getSize()));

        Border margin = new EmptyBorder(10, 10, 10, 10);
        lblEmail.setBorder(margin);
        txtEmail.setBorder(margin);
        btnReset.setBorder(margin);
        lblEmail.setText("Enter the email address to reset the password");
        btnReset.setText("Reset Password");

        Action resetPasswordAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                UserService userService = new UserService();
                boolean resetStatus = userService.resetPassword(email);

                if (resetStatus)
                    dispose();
            }
        };

        btnReset.addActionListener(resetPasswordAction);

        txtEmail.addActionListener(resetPasswordAction);


    }
}
