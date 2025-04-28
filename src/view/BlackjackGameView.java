package view;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BlackjackGameView extends JPanel {

    private JLabel balanceLabel;
    private JLabel betLabel;
    private JLabel card1;
    private JLabel card2;

    public BlackjackGameView() {
        setLayout(new BorderLayout());
        setBackground(new Color(24, 56, 10)); // casino felt green

        // Center area for cards
        JPanel cardsPanel = createCardsPanel();

        // Side panel for balance, bet, and buttons
        JPanel infoPanel = createInfoPanel();

        // Bottom area for back button
        JPanel backButton = createBackButton();

        add(cardsPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
        add(backButton, BorderLayout.SOUTH);
    }

    private JPanel createCardsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 56, 10));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50)); // Centered with spacing

        card1 = createCard(); // save references now
        card2 = createCard();

        panel.add(card1);
        panel.add(card2);

        return panel;
    }

    private JLabel createCard() {
        JLabel card = new JLabel(getRandomCardText(), SwingConstants.CENTER);
        card.setPreferredSize(new Dimension(80, 120));
        card.setOpaque(true);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        card.setFont(new Font("SansSerif", Font.BOLD, 24));

        // Random red or black text
        if (new Random().nextBoolean()) {
            card.setForeground(Color.RED);
        } else {
            card.setForeground(Color.BLACK);
        }

        return card;
    }

    private String getRandomCardText() {
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        Random rand = new Random();
        return values[rand.nextInt(values.length)];
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 56, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Balance label
        balanceLabel = new JLabel("Current account balance: $100");
        balanceLabel.setForeground(new Color(255, 215, 0)); // Gold
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bet label
        betLabel = new JLabel("Current hand bet: $10");
        betLabel.setForeground(new Color(255, 215, 0)); // Gold
        betLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        betLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bet buttons
        JButton increaseBet = new JButton("+$10 bet");
        JButton decreaseBet = new JButton("-$10 bet");
        increaseBet.setAlignmentX(Component.CENTER_ALIGNMENT);
        decreaseBet.setAlignmentX(Component.CENTER_ALIGNMENT);

        increaseBet.setBackground(new Color(50, 50, 50));
        increaseBet.setForeground(Color.WHITE);
        decreaseBet.setBackground(new Color(50, 50, 50));
        decreaseBet.setForeground(Color.WHITE);

        // Hit and Stand buttons (side by side)
        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setBackground(new Color(24, 56, 10));
        actionButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton hitButton = new JButton("Hit");
        JButton standButton = new JButton("Stand");

        hitButton.setBackground(new Color(50, 50, 50));
        hitButton.setForeground(Color.WHITE);
        standButton.setBackground(new Color(50, 50, 50));
        standButton.setForeground(Color.WHITE);

        actionButtonsPanel.add(hitButton);
        actionButtonsPanel.add(standButton);

        // Add components to side panel
        panel.add(balanceLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(betLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(increaseBet);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(decreaseBet);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(actionButtonsPanel);

        return panel;
    }

    private JPanel createBackButton() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));

        JButton backBtn = new JButton("Back to Main");
        backBtn.setPreferredSize(new Dimension(0, 50));
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
        backBtn.setBackground(new Color(50, 50, 50));
        backBtn.setForeground(Color.BLACK);

        backBtn.addActionListener(e -> CasinoUI.showView("MainView"));

        panel.add(backBtn, BorderLayout.CENTER);
        return panel;
    }

    // 🆕 Public method to refresh cards (called when view is shown)
    public void refreshCards() {
        card1.setText(getRandomCardText());
        card2.setText(getRandomCardText());

        // Randomize color again
        card1.setForeground(new Random().nextBoolean() ? Color.RED : Color.BLACK);
        card2.setForeground(new Random().nextBoolean() ? Color.RED : Color.BLACK);
    }
}
