package view;

import controller.NewUserController;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.User;

public class NewUserView extends JPanel {
    private JTextField nameField;
    private JPasswordField passField;
    private JLabel msgLabel;
    private JButton createBtn;
    private NewUserController newUserController;

    public NewUserView(NewUserController controller) {
        this.newUserController = controller;

        setLayout(new GridBagLayout());
        setBackground(new Color(40, 40, 40));

        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(new Color(255, 215, 0));

        JLabel nameLabel = new JLabel("New Username:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        nameField = new JTextField(20);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nameField.setMargin(new Insets(5, 10, 5, 10));

        JLabel passLabel = new JLabel("New Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passField = new JPasswordField(20);
        passField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passField.setMargin(new Insets(5, 10, 5, 10));

        msgLabel = new JLabel(" ");
        msgLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        msgLabel.setForeground(Color.ORANGE);
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        msgLabel.setPreferredSize(new Dimension(360, 24));

        JPanel msgPanel = new JPanel();
        msgPanel.setBackground(new Color(40, 40, 40));
        msgPanel.setPreferredSize(new Dimension(360, 30));
        msgPanel.add(msgLabel);

        passField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { validatePassword(); }
            public void removeUpdate(DocumentEvent e) { validatePassword(); }
            public void insertUpdate(DocumentEvent e) { validatePassword(); }
        });

        createBtn = new JButton("Create Account");
        createBtn.setFocusPainted(false);
        createBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        createBtn.setBackground(new Color(80, 120, 255));
        createBtn.setForeground(Color.WHITE);
        createBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        createBtn.addActionListener(e -> {
            String username = nameField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (!isPasswordValid(password)) {
                JOptionPane.showMessageDialog(this, "Password is not strong enough.", "Weak Password", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (controller.createUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                User user = new User("generatedID", username, password, 0);
                CasinoUI.setCurrentUser(user);
                CasinoUI.setupPostLoginViews();
                CasinoUI.showView("MainView");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create account. Username may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 16, 12, 16);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(passLabel, gbc);
        gbc.gridx = 1;
        add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(msgPanel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createBtn, gbc);
    }

    private void validatePassword() {
        String password = new String(passField.getPassword());

        if (isPasswordValid(password)) {
            msgLabel.setText("Strong password");
            msgLabel.setForeground(Color.GREEN);
            return;
        }

        StringBuilder msg = new StringBuilder();
        if (!password.matches(".*[a-z].*")) {
            msg.append("Missing lowercase. ");
        }
        if (!password.matches(".*[A-Z].*")) {
            msg.append("Missing uppercase. ");
        }
        if (!password.matches(".*\\d.*")) {
            msg.append("Missing digit. ");
        }
        if (!password.matches(".*[@$!%*?&].*")) {
            msg.append("Missing symbol. ");
        }
        if (password.length() < 8) {
            msg.append("Too short (min 8 chars). ");
        }

        msgLabel.setText(msg.toString().trim());
        msgLabel.setForeground(Color.ORANGE);
    }


    private boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }
}
