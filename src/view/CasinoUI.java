package view;

import controller.*;
import model.SpendingLimit;
import model.Transaction;
import model.User;
import model.UserManager;
import view.UserView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CasinoUI {
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static User currentUser;
    private static NotificationController notificationController;
    private static SpendingLimit spendingLimitModel;
    private static UserManager userManager;
    private static Map<String, JPanel> views = new HashMap<>();



    static UserView userView = new UserView();

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


        // Login test credentials
        User testUser = new User("1", "testUser", "password123", "test@example.com", 100.0);
        LoginView loginView = new LoginView();
        UserAuthController authController = new UserAuthController(testUser, userView);

        // Login button behavior/authentication
        loginView.getLoginButton().addActionListener(e -> {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            if (authController.authenticate(username, password)) {
                currentUser = testUser;
                setupPostLoginViews();
                showView("MainView");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Initialize userManager if not already done
        if (userManager == null) {
            userManager = new UserManager(); // Assuming you have a default constructor
        }

        // New Account creation view
        NewUserController newUserController = new NewUserController(userManager);
        NewUserView newUserView = new NewUserView(newUserController);
        views.put("NewUserView", newUserView);
        mainPanel.add(newUserView, "NewUserView");

        // Add login view to card layout
        mainPanel.add(loginView, "LoginView");

        frame.add(mainPanel);
        frame.setVisible(true);

        showView("LoginView");
    }


    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void setupPostLoginViews() {
        // Clear or refresh views if needed
        spendingLimitModel = new SpendingLimit(notificationController);
        SpendingLimitView spendingLimitView = new SpendingLimitView();
        new SpendingLimitController(spendingLimitModel, spendingLimitView, currentUser.getUserId());

        TransactionController transactionController = new TransactionController(spendingLimitModel);
        Transaction deposit = new Transaction("001", 50.0, "deposit");
        transactionController.processTransaction(currentUser, deposit);

        mainPanel.add(new MainView(), "MainView");
        mainPanel.add(new GameView(), "GameView");
        mainPanel.add(spendingLimitView, "SpendingLimitView");
        mainPanel.add(new UserView(currentUser, notificationController), "UserView");

        notificationController.subscribe(currentUser.getUserId());
    }


    public static void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
    }


}