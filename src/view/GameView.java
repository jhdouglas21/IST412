package view;

import view.CasinoUI;
import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    public GameView() {
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 20));

        JLabel title = new JLabel("Casino Games", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        JLabel placeholder = new JLabel("Coming Soon...", SwingConstants.CENTER);
        placeholder.setFont(new Font("SansSerif", Font.PLAIN, 16));
        placeholder.setForeground(new Color(200, 200, 200));

        add(title, BorderLayout.NORTH);
        add(placeholder, BorderLayout.CENTER);
        add(createBackButton(), BorderLayout.SOUTH);
    }

    private JButton createBackButton() {
        JButton backBtn = new JButton("Back to Main");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        backBtn.setBackground(new Color(50, 50, 50));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        backBtn.addActionListener(e -> CasinoUI.showView("MainView"));
        return backBtn;
    }
}