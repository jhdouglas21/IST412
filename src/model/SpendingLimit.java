package model;

import controller.NotificationController;
import java.util.HashMap;
import java.util.Map;

public class SpendingLimit {
    private Map<String, Double> userLimits;
    private Map<String, Double> userSpending;
    private NotificationController notificationController;

    public SpendingLimit(NotificationController notificationController) {
        userLimits = new HashMap<>();
        userSpending = new HashMap<>();
        this.notificationController = notificationController;
    }

    public void setSpendingLimit(String userId, double limit) {
        userLimits.put(userId, limit);
    }

    public double getSpendingLimit(String userId) {
        return userLimits.getOrDefault(userId, 0.0);
    }

    // ensures amount is less than spending limit
    public boolean canSpend(String userId, double amount) {
        double spent = userSpending.getOrDefault(userId, 0.0);
        double limit = userLimits.getOrDefault(userId, Double.MAX_VALUE);
        return (spent + amount) <= limit;
    }

    public void addTransaction(String userId, double amount) {
        double newSpending = userSpending.getOrDefault(userId, 0.0) + amount;
        userSpending.put(userId, newSpending);

        double limit = userLimits.getOrDefault(userId, Double.MAX_VALUE);
        if (newSpending >= 0.9 * limit && notificationController.isSubscribed(userId)) {
            notificationController.sendUserNotification(userId,"Spending Limit", "Youve used 90% of your spending limit.");
        }

        if (newSpending >= limit && notificationController.isSubscribed(userId)) {
            notificationController.sendUserNotification(userId, "Spending Limit","Youve exceeded your spending limit!");
        }
    }

    public void resetSpending(String userId) {
        userSpending.put(userId, 0.0);
    }
}
