package controller;

import model.DailyLimitStrategy;
import model.SpendingLimit;
import model.Transaction;
import model.User;

public class TransactionController {
    private SpendingLimit spendingLimit;

    public TransactionController(SpendingLimit spendingLimit) {
        this.spendingLimit = spendingLimit;
    }

    // analyzes transaction type and amount
    public boolean processTransaction(User user, Transaction transaction) {
        if (transaction == null || user == null) {
            return false; // false if input is invalid
        }

        double transactionAmount = transaction.getAmount();

        if (transactionAmount <= 0) {
            return false; // false if transaction amount is negative
        }

        if (transaction.getType().equalsIgnoreCase("deposit")) {
            return deposit(user, transactionAmount);
        } else if (transaction.getType().equalsIgnoreCase("bet")) {
            DailyLimitStrategy strategy = new DailyLimitStrategy(spendingLimit);
            if (!spendingLimit.canSpend(user, transactionAmount, strategy)) {
                return placeBet(user, transactionAmount);
            } else {
                System.out.println("Transaction denied: spending limit exceeded.");
                return false;
            }
        } else {
            return false;
        }
    }

    // adds deposit to balance
    public boolean deposit(User user, double amount) {
        user.setBalance(user.getBalance() + amount);
        logTransaction(user, "Deposit", amount);
        return true;
    }

    // processes a bet
    public boolean placeBet(User user, double amount) {
        if (user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            spendingLimit.addTransaction(user.getUserId(), amount);
            logTransaction(user, "Bet", amount);
            return true;
        }
        return false; // not enough funds
    }

    // logs the transaction
    private void logTransaction(User user, String type, double amount) {
        System.out.println("Transaction Logged: User: " + user.getUsername() + ", Type: " + type + ", Amount: $" + amount);
    }
}
