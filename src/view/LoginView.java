package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    public LoginView() {
        setLayout(new GridBagLayout());
        setBackground(new Color(40, 40, 40));

        JLabel title = new JLabel("Casino Login");
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.BLACK);

        createAccountButton = new JButton("Create Account"); // fixed here
        createAccountButton.setBackground(new Color(100, 200, 100));
        createAccountButton.setForeground(Color.BLACK);
        createAccountButton.addActionListener(e -> CasinoUI.showView("NewUserView"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(userLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(passLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        gbc.gridy++; // Add the "Create Account" button below the login button
        add(createAccountButton, gbc);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword()).trim();
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
