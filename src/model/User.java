package model;

import javax.swing.*;
import java.awt.*;

public class User extends Component {

    private String cardNumber;
    private String cardExpiry;
    private String cardCVV;

    public User(String number, String testUser, String password123, String mail, double v) {
    }

    public void setCardInfo(String cardNumber, String expiry, String cvv) {
        this.cardNumber = cardNumber;
        this.cardExpiry = expiry;
        this.cardCVV = cvv;

        JOptionPane.showMessageDialog(this, "Card Information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getCardNumber() { return cardNumber; }
    public String getCardExpiry() { return cardExpiry; }
    public String getCardCVV() { return cardCVV; }

    private String userId;
    public String username;
    private String password;
    private double balance;

    public User(String userId, String username, String password, double balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    //gets user id
    public String getUserId() {
        return userId;
    }

    //gets password
    public String getUserPassword() {
        return password;
    }

    //gets username
    public String getUsername() {
        return username;
    }


    //gets user balance
    public double getBalance() {
        return balance;
    }

    //updates user balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    //authenticates user login credentials
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void setUsername(String username) {
        JOptionPane.showMessageDialog(this, "Name saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.username = username;
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;

        // Regex explanation below
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(pattern);
    }


    public boolean updatePassword(String oldPass, String newPass) {
        if (!isValidPassword(newPass)) {
            JOptionPane.showMessageDialog(this, "New password is not strong!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false; // or throw an error or log a warning
        }

        if (oldPass.equals(this.password)) {
            this.password = newPass;
            JOptionPane.showMessageDialog(this, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else{
            JOptionPane.showMessageDialog(this, "Current password is not correct!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }


    //resetting user password
    public boolean resetPassword() {
        return true;
    }}

    // In User.java

