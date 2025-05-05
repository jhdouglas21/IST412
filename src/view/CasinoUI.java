package view;

import controller.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import model.SpendingLimit;
import model.Transaction;
import model.User;
import model.UserManager;

public class CasinoUI {

    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static User currentUser;
    private static NotificationController notificationController;
    private static SpendingLimit spendingLimitModel;
    private static TransactionController transactionController;
    private static UserManager userManager;
    private static Map<String, JPanel> views = new HashMap<>();

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

        notificationController = NotificationController.getInstance();

        User testUser = new User("1", "testUser", "password123", 100.0);
        LoginView loginView = new LoginView();
        UserView dummyView = new UserView();
        UserAuthController authController = new UserAuthController(testUser, dummyView);

        loginView.getLoginButton().addActionListener(e -> {
            if (authController.authenticate(loginView.getUsername(), loginView.getPassword())) {
                currentUser = testUser;
                setupPostLoginViews();
                showView("MainView");
            } else {
                JOptionPane.showMessageDialog(frame, 
                    "Invalid credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        if (userManager == null) userManager = new UserManager();
        NewUserController newUserController = new NewUserController(userManager);
        NewUserView newUserView = new NewUserView(newUserController);
        views.put("NewUserView", newUserView);

        mainPanel.add(newUserView, "NewUserView");
        mainPanel.add(loginView, "LoginView");
        frame.add(mainPanel);
        frame.setVisible(true);
        showView("LoginView");
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setupPostLoginViews() {
        spendingLimitModel = new SpendingLimit(notificationController);
        SpendingLimitView spendingLimitView = new SpendingLimitView();
        new SpendingLimitController(
            spendingLimitModel, spendingLimitView, currentUser.getUserId(), currentUser);

        transactionController = new TransactionController(spendingLimitModel);

        Transaction deposit = new Transaction("001", 50.0, "deposit");
        transactionController.processTransaction(currentUser, deposit);

        mainPanel.add(new MainView(), "MainView");
        mainPanel.add(new GameView(), "GameView");
        mainPanel.add(spendingLimitView, "SpendingLimitView");
        mainPanel.add(new UserView(currentUser, notificationController), "UserView");
        mainPanel.add("BlackjackGameView", new BlackjackGameView());

        notificationController.subscribe(currentUser.getUserId());
    }

    public static TransactionController getTransactionController() {
        return transactionController;
    }

    public static void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
    }
}
