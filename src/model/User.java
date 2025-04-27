package model;

public class User {

    private String cardNumber;
    private String cardExpiry;
    private String cardCVV;

    public void setCardInfo(String cardNumber, String expiry, String cvv) {
        this.cardNumber = cardNumber;
        this.cardExpiry = expiry;
        this.cardCVV = cvv;
    }

    public String getCardNumber() { return cardNumber; }
    public String getCardExpiry() { return cardExpiry; }
    public String getCardCVV() { return cardCVV; }

    private String userId;
    public String username;
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

    public User(String username, String password) {
        this.password = password;
        this.username = username;
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

    public void setUsername(String username) {
        this.username = username;
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

    // In User.java

