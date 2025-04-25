package view;

import controller.NotificationController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class UserView extends JPanel {

    private JButton backButton, nextButton, finishButton;
    private JTextField limitAmountField;
    private JComboBox<String> timeFrameCombo;
    private JCheckBox notifyCheckbox, blockCheckbox;
    public UserView() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("User Profile", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setOpaque(false);

        JLabel firstNameLabel = new JLabel("FirstName: ");
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField firstNameTF = new JTextField(15);
        firstNameTF.setMaximumSize(firstNameTF.getPreferredSize());
        firstNameTF.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton saveBtn1 = new JButton("Save");
        saveBtn1.setBackground(new Color(50, 50, 50));
        saveBtn1.setForeground(Color.WHITE);

        JLabel lastNameLabel = new JLabel("LastName: ");
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField lastNameTF = new JTextField(15);
        lastNameTF.setMaximumSize(lastNameTF.getPreferredSize());
        lastNameTF.setAlignmentX(Component.CENTER_ALIGNMENT);

    

        userPanel.add(firstNameLabel);
        userPanel.add(firstNameTF);
        userPanel.add(saveBtn1);

        userPanel.add(lastNameLabel);
        userPanel.add(lastNameTF);
        userPanel.add(saveBtn1);

        add(title, BorderLayout.NORTH);
        add(userPanel, BorderLayout.CENTER);
        add(createBackButton(), BorderLayout.SOUTH);
    }

    public UserView(User currentUser, NotificationController notificationController) {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));
    
        JLabel title = new JLabel("User Profile", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
    
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setOpaque(false);
    
        JLabel userLabel = new JLabel("Username: " + currentUser.getUsername());
        userLabel.setForeground(Color.WHITE);
        userLabel.setAlignmentX(LEFT_ALIGNMENT);
    
        JLabel emailLabel = new JLabel("Email: " + currentUser.getEmail());
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setAlignmentX(LEFT_ALIGNMENT);
    
        JLabel balanceLabel = new JLabel("Balance: $" + currentUser.getBalance());
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setAlignmentX(LEFT_ALIGNMENT);
    
        userPanel.add(userLabel);
        userPanel.add(emailLabel);
        userPanel.add(balanceLabel);
        userPanel.add(Box.createRigidArea(new Dimension(0, 20)));
    
        // Notification section
        JLabel notifLabel = new JLabel("Subscribe to Notifications");
        notifLabel.setForeground(Color.WHITE);
        notifLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        notifyCheckbox = new JCheckBox("Opt in to receive all notifications");
        notifyCheckbox.setForeground(Color.WHITE);
        notifyCheckbox.setOpaque(false);
        notifyCheckbox.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JLabel notifTypesLabel = new JLabel("Choose the types of notifications you would like to receive:");
        notifTypesLabel.setForeground(Color.WHITE);
        notifTypesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JCheckBox spendingLimitCheckbox = new JCheckBox("Spending Limits");
        JCheckBox transactionsCheckbox = new JCheckBox("Transactions");
        JCheckBox gameAlertsCheckbox = new JCheckBox("Game Alerts");
    
        for (JCheckBox cb : new JCheckBox[]{spendingLimitCheckbox, transactionsCheckbox, gameAlertsCheckbox}) {
            cb.setForeground(Color.WHITE);
            cb.setOpaque(false);
            cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    
        JButton savePrefsBtn = new JButton("Save Preferences");
        savePrefsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        savePrefsBtn.setBackground(new Color(70, 130, 180));
        savePrefsBtn.setForeground(Color.BLACK);
    
        savePrefsBtn.addActionListener(e -> {
            boolean optIn = notifyCheckbox.isSelected();
            boolean wantsSpending = spendingLimitCheckbox.isSelected();
            boolean wantsTransactions = transactionsCheckbox.isSelected();
            boolean wantsGameAlerts = gameAlertsCheckbox.isSelected();
    
            notificationController.savePreferences(currentUser.getUsername(), optIn, wantsSpending, wantsTransactions, wantsGameAlerts);
    
            JOptionPane.showMessageDialog(this, "Preferences saved successfully!", "Notification Settings", JOptionPane.INFORMATION_MESSAGE);
        });
    
        userPanel.add(notifLabel);
        userPanel.add(notifyCheckbox);
        userPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        userPanel.add(notifTypesLabel);
        userPanel.add(spendingLimitCheckbox);
        userPanel.add(transactionsCheckbox);
        userPanel.add(gameAlertsCheckbox);
        userPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        userPanel.add(savePrefsBtn);
    
        add(title, BorderLayout.NORTH);
        add(userPanel, BorderLayout.CENTER);
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
