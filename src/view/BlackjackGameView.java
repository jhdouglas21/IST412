package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BlackjackGameView extends JPanel {

    private JLabel balanceLabel;
    private JLabel betLabel;
    private JLabel myHandLabel;
    private JLabel dealerHandLabel;
    private JLabel resultLabel;

    private JPanel cardsPanel;
    private ArrayList<JLabel> myCards;
    private int myHandTotal;
    private int currentBet;
    private int balance;

    private JButton hitButton;
    private JButton standButton;
    private JButton playAgainButton;

    public BlackjackGameView() {
        setLayout(new BorderLayout());
        setBackground(new Color(24, 56, 10)); // casino felt green

        myCards = new ArrayList<>();
        myHandTotal = 0;
        currentBet = 10; // start at 10
        balance = 100;   // start balance 100

        cardsPanel = createCardsPanel();
        JPanel infoPanel = createInfoPanel();
        JPanel backButton = createBackButton();

        add(cardsPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
        add(backButton, BorderLayout.SOUTH);

        addCard();
        addCard();
    }

    private JPanel createCardsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 56, 10));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        return panel;
    }

    private JLabel createCard() {
        String value = getRandomCardText();
        JLabel card = new JLabel(value, SwingConstants.CENTER);
        card.setPreferredSize(new Dimension(80, 120));
        card.setOpaque(true);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        card.setFont(new Font("SansSerif", Font.BOLD, 24));

        if (new Random().nextBoolean()) {
            card.setForeground(Color.RED);
        } else {
            card.setForeground(Color.BLACK);
        }

        myHandTotal += getCardValue(value);

        return card;
    }

    private String getRandomCardText() {
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        Random rand = new Random();
        return values[rand.nextInt(values.length)];
    }

    private int getCardValue(String value) {
        switch (value) {
            case "A": return 11;
            case "J":
            case "Q":
            case "K": return 10;
            default: return Integer.parseInt(value);
        }
    }

    private void addCard() {
        JLabel newCard = createCard();
        myCards.add(newCard);
        cardsPanel.add(newCard);
        updateHandLabel();
        revalidate();
        repaint();

        if (myHandTotal > 21) {
            resultLabel.setText("You bust! You lose!");
            balance -= currentBet; // loose bet immediately if bust
            updateBalanceLabel();
            disableGameButtons();
            playAgainButton.setVisible(true);
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Current account balance: $" + balance);
    }


    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 56, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        balanceLabel = new JLabel("Current account balance: $" + balance);
        balanceLabel.setForeground(new Color(255, 215, 0));
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        betLabel = new JLabel("Current hand bet: $" + currentBet);
        betLabel.setForeground(new Color(255, 215, 0));
        betLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        betLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        myHandLabel = new JLabel("My hand total: 0");
        myHandLabel.setForeground(Color.WHITE);
        myHandLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        myHandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dealerHandLabel = new JLabel("Dealer hand: -");
        dealerHandLabel.setForeground(Color.WHITE);
        dealerHandLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        dealerHandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultLabel = new JLabel("");
        resultLabel.setForeground(Color.YELLOW);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton increaseBet = new JButton("+$10 bet");
        JButton decreaseBet = new JButton("-$10 bet");
        increaseBet.setAlignmentX(Component.CENTER_ALIGNMENT);
        decreaseBet.setAlignmentX(Component.CENTER_ALIGNMENT);

        increaseBet.setBackground(new Color(50, 50, 50));
        increaseBet.setForeground(Color.WHITE);
        decreaseBet.setBackground(new Color(50, 50, 50));
        decreaseBet.setForeground(Color.WHITE);

        increaseBet.addActionListener(e -> {
            if (currentBet + 10 <= balance) {
                currentBet += 10;
                updateBetLabel();
            }
        });

        decreaseBet.addActionListener(e -> {
            if (currentBet >= 10) {
                currentBet -= 10;
                updateBetLabel();
            }
        });

        // Hit/Stand buttons
        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setBackground(new Color(24, 56, 10));
        actionButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");

        hitButton.setBackground(new Color(50, 50, 50));
        hitButton.setForeground(Color.WHITE);
        standButton.setBackground(new Color(50, 50, 50));
        standButton.setForeground(Color.WHITE);

        actionButtonsPanel.add(hitButton);
        actionButtonsPanel.add(standButton);

        hitButton.addActionListener(e -> onHit());
        standButton.addActionListener(e -> onStand());

        playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.setBackground(new Color(255, 215, 0));
        playAgainButton.setForeground(Color.BLACK);
        playAgainButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        playAgainButton.setVisible(false);

        playAgainButton.addActionListener(e -> resetGame());

        panel.add(balanceLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(betLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(myHandLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(dealerHandLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(increaseBet);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(decreaseBet);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(actionButtonsPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(resultLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(playAgainButton);

        return panel;
    }

    private void updateHandLabel() {
        myHandLabel.setText("My hand total: " + myHandTotal);
    }

    private void updateBetLabel() {
        betLabel.setText("Current hand bet: $" + currentBet);
    }

    private void onHit() {
        addCard();
    }

    private void onStand() {
        Random rand = new Random();
        int dealerHand = rand.nextInt(13) + 14; // 14-26 (random for now just average card nums to add)
        dealerHandLabel.setText("Dealer hand: " + dealerHand);

        if ((dealerHand > myHandTotal && dealerHand <= 21) || myHandTotal > 21) {
            resultLabel.setText("You lose!");
            balance -= currentBet; // lose
        } else {
            resultLabel.setText("You win!");
            balance += currentBet; // win
        }

        updateBalanceLabel();
        disableGameButtons();
        playAgainButton.setVisible(true);
    }


    private void disableGameButtons() {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

    private void enableGameButtons() {
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
    }

    private void resetGame() {
        myCards.clear();
        cardsPanel.removeAll();

        myHandTotal = 0;
        addCard();
        addCard();

        dealerHandLabel.setText("Dealer hand: -");
        resultLabel.setText("");
        playAgainButton.setVisible(false);

        enableGameButtons();
        revalidate();
        repaint();
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
}
