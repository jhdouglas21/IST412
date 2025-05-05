package controller;


import model.User;
import view.UserView;

import javax.swing.*;

public class UserAuthController {
    private User model;
    private UserView view;

    public UserAuthController(User model, UserView view) {
        this.model = model;
        this.view = view;
    }

    //authenticates the user based on username and password
    public boolean authenticate(String username, String password) {
        //checks if the username and password match expected values
        boolean isAuthenticated = username.equals("testUser") && password.equals("password123");

        if (isAuthenticated) {
            JOptionPane.showMessageDialog(view, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Invalid Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return isAuthenticated;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (!User.isValidPassword(newPassword)) {
            JOptionPane.showMessageDialog(view,
                    "Password must be at least 8 characters long and include uppercase, lowercase, numbers, and special characters.",
                    "Weak Password",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean updated = model.updatePassword(oldPassword, newPassword);
        if (updated) {
            JOptionPane.showMessageDialog(view,
                    "Password updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view,
                    "Old password is incorrect or new password is invalid.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return updated;
    }


    //gets username of user
    public String getUsername() {
        return model.getUsername();
    }

    //gets user's current balance
    public double getUserBalance() {
        return model.getBalance();
    }

    //updates user's balance
    public boolean updateUserBalance(double newBalance) {
        if (newBalance < 0) {
            JOptionPane.showMessageDialog(view, "Balance cannot be negative!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        model.setBalance(newBalance);
        JOptionPane.showMessageDialog(view, "Balance updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    //displays user details
    public void displayUserInfo() {
        String userInfo = "Username: " + getUsername() + "\nBalance: $" + getUserBalance();
        JOptionPane.showMessageDialog(view, userInfo, "User Info", JOptionPane.INFORMATION_MESSAGE);
    }
}