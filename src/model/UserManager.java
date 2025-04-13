package model;

import java.util.HashMap;
import java.util.UUID;

public class UserManager {
    private HashMap<String, User> users = new HashMap<>();

    public boolean addUser(String username, String password, String email) {
        if (users.containsKey(username)) {
            return false; // Username taken
        }

        String userId = UUID.randomUUID().toString();
        User user = new User(userId, username, password, email, 0.0);
        users.put(username, user);
        return true;
    }

    // In UserManager.java
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }


    public User getUser(String username) {
        return users.get(username);
    }

    public boolean usernameExists(String username) {
        return users.containsKey(username);
    }

    public HashMap<String, User> getAllUsers() {
        return users;
    }
}
