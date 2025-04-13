package model;

public class PasswordStrengthChecker {

    public enum Strength {
        WEAK, OK, STRONG
    }

    public static Strength evaluatePassword(String password) {
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.matches("(?=.*[0-9]).*")) score++; // contains digit
        if (password.matches("(?=.*[a-z])(?=.*[A-Z]).*")) score++; // upper & lower case
        if (password.matches("(?=.*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?]).*")) score++; // special char

        if (score <= 1) return Strength.WEAK;
        else if (score == 2 || score == 3) return Strength.OK;
        else return Strength.STRONG;
    }
}

