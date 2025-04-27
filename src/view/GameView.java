package view;

import model.*;
import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private JTextArea gameOutput;

    public GameView() {
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 20));

        JLabel title = new JLabel("Casino Games", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        JPanel buttonPanel = createGameButtons();
        gameOutput = new JTextArea("Game output will appear here...");
        gameOutput.setEditable(false);
        gameOutput.setBackground(new Color(30, 30, 30));
        gameOutput.setForeground(Color.GREEN);
        gameOutput.setFont(new Font("Monospaced", Font.PLAIN, 14));
        gameOutput.setMargin(new Insets(10, 10, 10, 10));

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(gameOutput, BorderLayout.SOUTH);
        add(createBackButton(), BorderLayout.SOUTH);
    }

    private JPanel createGameButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBackground(new Color(20, 20, 20));

        JButton blackjackBtn = createGameButton("Blackjack", new BlackjackFactory());
        JButton pokerBtn = createGameButton("Poker", new PokerFactory());
        JButton rouletteBtn = createGameButton("Roulette", new RouletteFactory());

        panel.add(blackjackBtn);
        panel.add(pokerBtn);
        panel.add(rouletteBtn);

        return panel;
    }

    private JButton createGameButton(String name, GameFactory factory) {
        JButton button = new JButton(name);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(new Color(50, 50, 50));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        button.addActionListener(e -> {
            CasinoGame game = factory.createGame();

            // For now just print output to the JTextArea
            gameOutput.setText(""); // Clear old text
            gameOutput.append("Launching " + name + "...\n");

            // Instead of System.out.println in game.play(), you can redirect output here
            gameOutput.append(">>> ");
            game.play();
            gameOutput.append("Game started.");
        });

        return button;
    }

    private JPanel createBackButton() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        JButton backBtn = new JButton("Back to Main");
        backBtn.setPreferredSize(new Dimension(0, 50));
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
        backBtn.setBackground(new Color(50, 50, 50));
        backBtn.setForeground(Color.WHITE);

        backBtn.addActionListener(e -> CasinoUI.showView("MainView"));

        panel.add(backBtn, BorderLayout.CENTER);

        return panel;
    }
}
