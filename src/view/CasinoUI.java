package view;

import controller.NotificationController;
import controller.SpendingLimitController;
import controller.TransactionController;
import controller.UserAuthController;
import model.SpendingLimit;
import model.Transaction;
import model.User;

import javax.swing.*;
import java.awt.*;

public class CasinoUI {
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static User currentUser;
    private static NotificationController notificationController;
    private static SpendingLimit spendingLimitModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CasinoUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Casino App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        notificationController = new NotificationController();

        // Login test credentials
        User testUser = new User("1", "testUser", "password123", "test@example.com", 100.0, "Placeholder", "Placeholder");
        LoginView loginView = new LoginView();
        UserAuthController authController = new UserAuthController(testUser, LoginView);

        // Login button behavior/authentication
        loginView.getLoginButton().addActionListener(e -> {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            if (authController.authenticate(username, password)) {
                currentUser = testUser; // Save the logged-in user
                setupPostLoginViews();
                showView("MainView");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Login view
        mainPanel.add(loginView, "LoginView");

        frame.add(mainPanel);
        frame.setVisible(true);

        showView("LoginView");
    }

    private static void setupPostLoginViews() {
        // Spending Limit view
        spendingLimitModel = new SpendingLimit(notificationController);
        SpendingLimitView spendingLimitView = new SpendingLimitView();
        new SpendingLimitController(spendingLimitModel, spendingLimitView, currentUser.getUserId());

        // Transaction Controller
        TransactionController transactionController = new TransactionController(spendingLimitModel);

        // test deposit
        Transaction deposit = new Transaction("001", 50.0, "deposit");
        transactionController.processTransaction(currentUser, deposit);

        // Views in mainview
        mainPanel.add(new MainView(), "MainView");
        mainPanel.add(new GameView(), "GameView");
        mainPanel.add(spendingLimitView, "SpendingLimitView");


        mainPanel.add(new UserView(currentUser, notificationController), "UserView");


        // Observation pattern notification subscription
        notificationController.subscribe(currentUser.getUserId());
    }

    public static void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
    }
}