package model;

public class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private double balance;

    public User(String userId, String username, String password, String email, double balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
    }

    //gets user id
    public String getUserId() {
        return userId;
    }

    //gets username
    public String getUsername() {
        return username;
    }

    //gets email
    public String getEmail() {
        return email;
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

    public static boolean isValidPassword(String password) {
        if (password == null) return false;

        // Regex explanation below
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(pattern);
    }


    public boolean updatePassword(String oldPassword, String newPassword) {
        if (!this.password.equals(oldPassword)) return false;

        if (!isValidPassword(newPassword)) {
            return false; // or throw an error or log a warning
        }

        this.password = newPassword;
        return true;
    }


    //resetting user password
    public boolean resetPassword() {
        return true;
    }}