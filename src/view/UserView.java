package view;

import controller.NotificationController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserView extends JPanel {

    private JCheckBox notifBox, spendBox, transactBox, gameBox;
    private User user;
    private NotificationController notifController;
    private List<JTextField> textFields = new ArrayList<>();
    private List<JPasswordField> passFields = new ArrayList<>();
    private JLabel balanceLabel;
    private JLabel usernameLabel;
    private JTextField usernameField, cardNumField, expiryField, cvvField;
    private JPasswordField oldPassField, newPassField, confirmPassField;

    public UserView(User user, NotificationController notifController) {
        this.user = user;
        this.notifController = notifController;

        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        JLabel titleLabel = new JLabel("User Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(30, 30, 30));

        mainPanel.add(userSection());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(passSection());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(cardSection());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(balanceSection());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(notifSection());

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel(), BorderLayout.SOUTH);
    }

    public UserView() {

    }

    private JPanel userSection() {
        JPanel panel = sectionPanel();

        JLabel sectionTitle = centeredTitle("Change Username");
        usernameLabel = centeredLabel("Current Username: " + user.getUsername());
        usernameField = textField(user.getUsername());

        panel.add(sectionTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(usernameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(formRow("New Username:", usernameField));

        return panel;
    }

    private JPanel passSection() {
        JPanel panel = sectionPanel();

        JLabel sectionTitle = centeredTitle("Change Password");
        oldPassField = passField();
        newPassField = passField();
        confirmPassField = passField();

        panel.add(sectionTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(formRow("Old Password:", oldPassField));
        panel.add(formRow("New Password:", newPassField));
        panel.add(formRow("Confirm Password:", confirmPassField));

        return panel;
    }

    private JPanel cardSection() {
        JPanel panel = sectionPanel();

        JLabel sectionTitle = centeredTitle("Credit Card Info");
        cardNumField = textField("");
        expiryField = textField("");
        cvvField = textField("");

        panel.add(sectionTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(formRow("Card Number:", cardNumField));
        panel.add(formRow("Expiration (MM/YY):", expiryField));
        panel.add(formRow("CVV:", cvvField));

        return panel;
    }

    private JPanel notifSection() {
        JPanel panel = sectionPanel();

        JLabel notifTitle = centeredTitle("Notification Settings");
        notifBox = checkBox("Opt in to all notifications");
        spendBox = checkBox("Spending Alerts");
        transactBox = checkBox("Transaction Alerts");
        gameBox = checkBox("Game Alerts");

        panel.add(notifTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(notifBox);
        panel.add(spendBox);
        panel.add(transactBox);
        panel.add(gameBox);

        return panel;
    }

    private JPanel bottomPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 0));
        panel.setBackground(new Color(30, 30, 30));

        JButton saveBtn = mainBtn("Save Changes");
        saveBtn.addActionListener(e -> {
            saveChanges();
            JOptionPane.showMessageDialog(this, "All changes saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearAllFields();
        });

        JButton backBtn = mainBtn("Back to Main");
        backBtn.addActionListener(e -> {
            clearAllFields();
            CasinoUI.showView("MainView");
        });

        panel.add(saveBtn);
        panel.add(backBtn);

        return panel;
    }

    private void saveChanges() {
        if (!usernameField.getText().trim().isEmpty()) {
            user.setUsername(usernameField.getText().trim());
            usernameLabel.setText("Current Username: " + user.getUsername());
        }

        String oldPass = new String(oldPassField.getPassword());
        String newPass = new String(newPassField.getPassword());
        String confirmPass = new String(confirmPassField.getPassword());

        if (!newPass.isEmpty() && newPass.equals(confirmPass)) {
            user.updatePassword(oldPass, newPass);
        }

        if (!cardNumField.getText().trim().isEmpty() || !expiryField.getText().trim().isEmpty() || !cvvField.getText().trim().isEmpty()) {
            user.setCardInfo(cardNumField.getText().trim(), expiryField.getText().trim(), cvvField.getText().trim());
        }

        notifController.savePreferences(
                user.getUsername(),
                notifBox.isSelected(),
                spendBox.isSelected(),
                transactBox.isSelected(),
                gameBox.isSelected()
        );
    }

    private void clearAllFields() {
        for (JTextField field : textFields) {
            field.setText("");
        }
        for (JPasswordField field : passFields) {
            field.setText("");
        }
    }


    private JPanel balanceSection() {
        JPanel panel = sectionPanel();

        JLabel sectionTitle = centeredTitle("Balance Management");
        balanceLabel = centeredLabel("Current Balance: $" + user.getBalance());
        JTextField amountField = textField("0.00");
        amountField.setName("amountField");

        JButton depositBtn = smallBtn("Deposit");
        depositBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    user.setBalance(user.getBalance() + amount);
                    balanceLabel.setText("Current Balance: $" + user.getBalance());
                    JOptionPane.showMessageDialog(this, "Deposited Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton withdrawBtn = smallBtn("Withdraw");
        withdrawBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0 && amount <= user.getBalance()) {
                    user.setBalance(user.getBalance() - amount);
                    balanceLabel.setText("Current Balance: $" + user.getBalance());
                    JOptionPane.showMessageDialog(this, "Withdrawn Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid withdrawal amount!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);

        panel.add(sectionTitle);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(balanceLabel);
        panel.add(formRow("Amount:", amountField));
        panel.add(buttonPanel);

        return panel;
    }

    private JPanel sectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 30));
        return panel;
    }

    private JLabel centeredTitle(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel centeredLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JTextField textField(String text) {
        JTextField field = new JTextField(text);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        textFields.add(field);
        return field;
    }

    private JPasswordField passField() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        passFields.add(field);
        return field;
    }

    private JPanel formRow(String labelText, JComponent inputField) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(30, 30, 30));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setPreferredSize(new Dimension(150, 30));

        panel.add(label, BorderLayout.WEST);
        panel.add(inputField, BorderLayout.CENTER);
        return panel;
    }

    private JCheckBox checkBox(String text) {
        JCheckBox box = new JCheckBox(text);
        box.setForeground(Color.WHITE);
        box.setBackground(new Color(30, 30, 30));
        box.setFont(new Font("Arial", Font.PLAIN, 18));
        box.setAlignmentX(Component.CENTER_ALIGNMENT);
        return box;
    }

    private JButton mainBtn(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(0, 50));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground(new Color(50, 50, 50));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        return btn;
    }

    private JButton smallBtn(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(new Color(50, 50, 50));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(100, 40));
        return btn;
    }
}
