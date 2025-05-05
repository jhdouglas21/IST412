package view;

import controller.TransactionController;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import javax.swing.*;
import model.Transaction;
import model.User;

public class BlackjackGameView extends JPanel {

    private JLabel balanceLabel, betLabel, myHandLabel, dealerHandLabel, resultLabel;
    private JPanel cardsPanel;
    private ArrayList<JLabel> myCards;
    private int myHandTotal;
    private double currentBet;
    private JButton hitButton, standButton, playAgainButton;

    public BlackjackGameView() {
        setLayout(new BorderLayout());
        setBackground(new Color(24, 56, 10));

        myCards = new ArrayList<>();
        myHandTotal = 0;
        currentBet = 1.0;  // default bet

        cardsPanel = createCardsPanel();
        JPanel infoPanel = createInfoPanel();
        JPanel backBtnPanel = createBackButton();

        add(cardsPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
        add(backBtnPanel, BorderLayout.SOUTH);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateBalanceLabel();
            }
        });

        dealInitialCards();
    }

    private void dealInitialCards() {
        // place bet up front
        if (!placeBet()) {
            resultLabel.setText("Cannot place bet: insufficient funds or limit reached");
            disableGameButtons();
            playAgainButton.setVisible(true);
            updateBalanceLabel();
            return;
        }
        updateBalanceLabel();
        addCard(); addCard();
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
        JLabel card = createCard();
        myCards.add(card);
        cardsPanel.add(card);
        updateHandLabel();
        revalidate(); repaint();
        if (myHandTotal > 21) {
            resultLabel.setText("You bust! You lose!");
            updateBalanceLabel();
            disableGameButtons();
            playAgainButton.setVisible(true);
        }
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 56, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        balanceLabel = new JLabel();
        balanceLabel.setForeground(new Color(255, 215, 0));
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        betLabel = new JLabel("Current hand bet: $" + currentBet);
        betLabel.setForeground(new Color(255,215,0));
        betLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        betLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        myHandLabel = new JLabel("My hand total: 0");
        myHandLabel.setForeground(Color.WHITE);
        myHandLabel.setFont(new Font("SansSerif",Font.BOLD,16));
        myHandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dealerHandLabel = new JLabel("Dealer hand: -");
        dealerHandLabel.setForeground(Color.WHITE);
        dealerHandLabel.setFont(new Font("SansSerif",Font.BOLD,16));
        dealerHandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultLabel = new JLabel("");
        resultLabel.setForeground(Color.YELLOW);
        resultLabel.setFont(new Font("SansSerif",Font.BOLD,18));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton inc = new JButton("+$10 bet"), dec = new JButton("-$10 bet");
        inc.setAlignmentX(Component.CENTER_ALIGNMENT);
        dec.setAlignmentX(Component.CENTER_ALIGNMENT);
        inc.setBackground(new Color(50,50,50)); inc.setForeground(Color.WHITE);
        dec.setBackground(new Color(50,50,50)); dec.setForeground(Color.WHITE);

        inc.addActionListener(e -> {
            double bal = CasinoUI.getCurrentUser().getBalance();
            if (currentBet + 10 <= bal) {
                currentBet += 10; updateBetLabel();
            }
        });
        dec.addActionListener(e -> {
            if (currentBet >= 10) {
                currentBet -= 10; updateBetLabel();
            }
        });

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        actions.setBackground(new Color(24,56,10));
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        for (JButton b : new JButton[]{hitButton, standButton}) {
            b.setBackground(new Color(50,50,50));
            b.setForeground(Color.WHITE);
            actions.add(b);
        }
        hitButton.addActionListener(e -> addCard());
        standButton.addActionListener(e -> onStand());

        playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.setBackground(new Color(255,215,0));
        playAgainButton.setForeground(Color.BLACK);
        playAgainButton.setFont(new Font("SansSerif",Font.BOLD,16));
        playAgainButton.setVisible(false);
        playAgainButton.addActionListener(e -> resetGame());

        panel.add(balanceLabel);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(betLabel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(myHandLabel);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(dealerHandLabel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(inc);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(dec);
        panel.add(Box.createRigidArea(new Dimension(0,30)));
        panel.add(actions);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(resultLabel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(playAgainButton);

        updateBalanceLabel();
        updateBetLabel();

        return panel;
    }

    private void updateHandLabel() {
        myHandLabel.setText("My hand total: " + myHandTotal);
    }

    private void updateBetLabel() {
        betLabel.setText("Current hand bet: $" + currentBet);
    }

    private void updateBalanceLabel() {
        double bal = CasinoUI.getCurrentUser().getBalance();
        balanceLabel.setText("Current account balance: $" + bal);
    }

    private boolean placeBet() {
        TransactionController tx = CasinoUI.getTransactionController();
        User user = CasinoUI.getCurrentUser();
        Transaction t = new Transaction(UUID.randomUUID().toString(), currentBet, "bet");
        return tx.processTransaction(user, t);
    }

    private void onStand() {
        int dealerTotal = new Random().nextInt(13) + 14;
        dealerHandLabel.setText("Dealer hand: " + dealerTotal);

        boolean playerWins = (dealerTotal > 21) || (myHandTotal <= 21 && myHandTotal > dealerTotal);
        if (playerWins) {
            TransactionController tx = CasinoUI.getTransactionController();
            User user = CasinoUI.getCurrentUser();
            Transaction winTx = new Transaction(UUID.randomUUID().toString(), currentBet, "deposit");
            tx.processTransaction(user, winTx);
            resultLabel.setText("You win!");
        } else {
            resultLabel.setText("You lose!");
        }
        updateBalanceLabel();
        disableGameButtons();
        playAgainButton.setVisible(true);
    }

    private void disableGameButtons() {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

    private void resetGame() {
        myHandTotal = 0;
        cardsPanel.removeAll();
        resultLabel.setText("");
        playAgainButton.setVisible(false);
        enableGameButtons();
        dealInitialCards();
        revalidate();
        repaint();
    }

    private void enableGameButtons() {
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
    }

    private JPanel createBackButton() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(30,30,30));
        JButton b = new JButton("Back to Main");
        b.setPreferredSize(new Dimension(0,50));
        b.setFont(new Font("Arial",Font.BOLD,18));
        b.setBackground(new Color(50,50,50));
        b.setForeground(Color.BLACK);
        b.addActionListener(e -> CasinoUI.showView("MainView"));
        p.add(b, BorderLayout.CENTER);
        return p;
    }
}


