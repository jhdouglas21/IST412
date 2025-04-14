package controller;

import java.util.*;

public class NotificationController {

    // ---- Singleton ----
    private static NotificationController instance;

    private NotificationController() {}

    public static synchronized NotificationController getInstance() {
        if (instance == null) {
            instance = new NotificationController();
        }
        return instance;
    }

    // ---- Observer Pattern ----
    public interface NotificationObserver {
        void update(String userId, String category, String message);
    }

    private List<NotificationObserver> observers = new ArrayList<>();

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String userId, String category, String message) {
        for (NotificationObserver observer : observers) {
            observer.update(userId, category, message);
        }
    }

    // ---- Subscriber Preferences ----
    private Set<String> subscribers = new HashSet<>();
    private Map<String, Set<String>> userPreferences = new HashMap<>();

    public void subscribe(String userId) {
        subscribers.add(userId);
    }

    public void unsubscribe(String userId) {
        subscribers.remove(userId);
        userPreferences.remove(userId);
    }

    public boolean isSubscribed(String userId) {
        return subscribers.contains(userId);
    }

    public void setUserPreference(String userId, String category, boolean enabled) {
        userPreferences.putIfAbsent(userId, new HashSet<>());
        Set<String> prefs = userPreferences.get(userId);
        if (enabled) {
            prefs.add(category);
        } else {
            prefs.remove(category);
        }
    }

    public boolean isCategoryEnabled(String userId, String category) {
        return userPreferences.getOrDefault(userId, Collections.emptySet()).contains(category);
    }

    public void sendUserNotification(String userId, String category, String message) {
        if (!isSubscribed(userId) || !isCategoryEnabled(userId, category)) return;

        System.out.println("Notification to [" + userId + "] for [" + category + "]: " + message);
        logNotification(message);
        notifyObservers(userId, category, message);
    }

    private void logNotification(String message) {
        System.out.println("Notification Logged: " + message);
    }

    // ---- Command Pattern ----
    public interface CasinoCommand {
        void execute();
    }

    public class PlaceBetCommand implements CasinoCommand {
        private String userId;
        private double amount;

        public PlaceBetCommand(String userId, double amount) {
            this.userId = userId;
            this.amount = amount;
        }

        public void execute() {
            System.out.println(userId + " placed a bet of $" + amount);
            sendUserNotification(userId, "betting", "Your bet of $" + amount + " has been placed.");
        }
    }

    public class WithdrawCommand implements CasinoCommand {
        private String userId;
        private double amount;

        public WithdrawCommand(String userId, double amount) {
            this.userId = userId;
            this.amount = amount;
        }

        public void execute() {
            System.out.println(userId + " withdrew $" + amount);
            sendUserNotification(userId, "banking", "You withdrew $" + amount + " from your account.");
        }
    }

    public class CasinoInvoker {
        private Queue<CasinoCommand> commandQueue = new LinkedList<>();

        public void addCommand(CasinoCommand command) {
            commandQueue.add(command);
        }

        public void processCommands() {
            while (!commandQueue.isEmpty()) {
                commandQueue.poll().execute();
            }
        }
    }
}

