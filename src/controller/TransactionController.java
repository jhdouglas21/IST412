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
            return false;
        }
        double amount = transaction.getAmount();
        
        if (amount <= 0) {
            return false;
        }

        if (transaction.getType().equalsIgnoreCase("deposit")) {
            return deposit(user, amount);
        } else if (transaction.getType().equalsIgnoreCase("bet")) {
            DailyLimitStrategy strategy = new DailyLimitStrategy(spendingLimit);
            if (spendingLimit.canSpend(user, amount, strategy)) {
                return placeBet(user, amount);
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
        return false;
    }

    // logs the transaction
    private void logTransaction(User user, String type, double amount) {
        System.out.println("Transaction Logged: User: " + user.getUsername() + ", Type: " + type + ", Amount: $" + amount);
    }
}
