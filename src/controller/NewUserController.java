package controller;

import model.PasswordStrengthChecker;
import model.User;
import view.NewUserView;
import model.UserManager;

public class NewUserController {
    private UserManager userManager;

    public NewUserController(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean createUser(String username, String password) {
        if (username == null || username.isEmpty() || password.length() < 6) {
            return false;
        }

        if (userManager.getUser(username) != null) {
            return false;
        }

        User newUser = new User(username, password);
        userManager.addUser(newUser);
        return true;
    }
}


