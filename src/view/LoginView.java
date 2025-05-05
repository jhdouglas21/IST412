package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private JTextField name;
    private JPasswordField pass;
    private JButton loginBtn;
    private JButton accountBtn;

    public LoginView() {
        setLayout(new GridBagLayout());
        setBackground(new Color(40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("The One and Only IST412 Casino");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(255, 215, 0));
        gbc.gridy = 0;
        add(title, gbc);

        JLabel nameLabel = new JLabel("Username:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridy++;
        add(nameLabel, gbc);

        name = new JTextField(20);
        name.setFont(new Font("SansSerif", Font.PLAIN, 14));
        name.setMargin(new Insets(5, 10, 5, 10));
        gbc.gridy++;
        add(name, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridy++;
        add(passLabel, gbc);

        pass = new JPasswordField(20);
        pass.setFont(new Font("SansSerif", Font.PLAIN, 14));
        pass.setMargin(new Insets(5, 10, 5, 10));
        gbc.gridy++;
        add(pass, gbc);

        loginBtn = new JButton("Login");
        styleBtn(loginBtn, new Color(45, 120, 200));
        gbc.gridy++;
        add(loginBtn, gbc);

        accountBtn = new JButton("Create Account");
        styleBtn(accountBtn, new Color(100, 100, 255));
        accountBtn.addActionListener(e -> CasinoUI.showView("NewUserView"));
        gbc.gridy++;
        add(accountBtn, gbc);
    }

    private void styleBtn(JButton btn, Color color) {
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public String getUsername() {
        return name.getText().trim();
    }

    public String getPassword() {
        return new String(pass.getPassword()).trim();
    }

    public JButton getLoginButton() {
        return loginBtn;
    }
}
