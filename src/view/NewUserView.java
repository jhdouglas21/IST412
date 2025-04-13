package view;

import controller.NewUserController;
import javax.swing.*;
import java.awt.*;
import model.User;

public class NewUserView extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel strengthLabel;
    private JButton createButton;

    private NewUserController newUserController;

    public NewUserView(NewUserController controller) {
        this.newUserController = controller;

        setLayout(new GridBagLayout());
        setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JLabel userLabel = new JLabel("New Username:");
        userLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(15);

        JLabel passLabel = new JLabel("New Password:");
        passLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(15);

        strengthLabel = new JLabel(" ");
        strengthLabel.setForeground(Color.YELLOW);

        passwordField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                checkStrength();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                checkStrength();
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                checkStrength();
            }
        });

        createButton = new JButton("Create Account");
        createButton.setBackground(new Color(70, 180, 100));
        createButton.setForeground(Color.BLACK);

        createButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (newUserController.createUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Set currentUser
                User newUser = new User("generatedID", username, password, "", 100.0); // You might want to generate a real ID and add email handling
                CasinoUI.setCurrentUser(newUser);

                // Setup post-login views and navigate to main
                CasinoUI.setupPostLoginViews();
                CasinoUI.showView("MainView");

            } else {
                JOptionPane.showMessageDialog(this, "Failed to create account. Username may already exist or password is weak.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
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
        add(strengthLabel, gbc);

        gbc.gridy++;
        add(createButton, gbc);
    }

    private void checkStrength() {
        String pass = new String(passwordField.getPassword());
        if (pass.length() < 6) {
            strengthLabel.setText("Password too short");
            strengthLabel.setForeground(Color.RED);
        } else if (pass.matches(".*[A-Z].*") && pass.matches(".*[0-9].*") && pass.length() >= 8) {
            strengthLabel.setText("Strong password");
            strengthLabel.setForeground(Color.GREEN);
        } else {
            strengthLabel.setText("Weak password (use numbers & uppercase)");
            strengthLabel.setForeground(Color.ORANGE);
        }
    }
}



