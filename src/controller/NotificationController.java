package controller;

import java.util.*;

public class NotificationController {
    private Set<String> subscribers = new HashSet<>();
    private Map<String, Set<String>> userPreferences = new HashMap<>();

    // subscribe and unsubscribe methods
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

    // allows opting in or out of notifs
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

    // sends notifications only if user is subscribed
    public void sendUserNotification(String userId, String category, String message) {
        if (!isSubscribed(userId) || !isCategoryEnabled(userId, category)) return;
        System.out.println("Notification to [" + userId + "] for [" + category + "]: " + message);
        logNotification(message);
    }

    private void logNotification(String message) {
        System.out.println("Notification Logged: " + message);
    }
}

