package view;

import view.CasinoUI;
import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    public MainView() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30)); // Dark background

        JLabel title = new JLabel("The One and Only IST412 Casino", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(new Color(255, 215, 0)); // Gold color

        JPanel btnPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        btnPanel.setOpaque(false);
        btnPanel.add(createNavButton("Play Game", "GameView"));
        btnPanel.add(createNavButton("Set Spending Limit", "SpendingLimitView"));
        btnPanel.add(createNavButton("User Profile", "UserView"));

        add(title, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.CENTER);
    }

    private JButton createNavButton(String text, String view) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn.setBackground(new Color(50, 50, 50));
        btn.setForeground(new Color(255, 215, 0)); // Gold text
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        btn.addActionListener(e -> CasinoUI.showView(view));
        return btn;
    }
}