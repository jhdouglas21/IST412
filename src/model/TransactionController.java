package model;

public class TransactionController {
    private SpendingLimit spendingLimit;

    public TransactionController(SpendingLimit spendingLimit) {
        this.spendingLimit = spendingLimit;
    }

    public boolean processTransaction(User user, Transaction transaction) {
        if (transaction == null || user == null) return false;

        double transactionAmount = transaction.getAmount();

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

    public boolean deposit(User user, double amount) {
        user.setBalance(user.getBalance() + amount);
        logTransaction(user, "Deposit", amount);
        return true;
    }

    public boolean placeBet(User user, double amount) {
        if (user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            spendingLimit.addTransaction(user.getUserId(), amount);
            logTransaction(user, "Bet", amount);
            return true;
        }
        return false;
    }

    private void logTransaction(User user, String type, double amount) {
        System.out.println("Transaction Logged: User: " + user.getUsername() + ", Type: " + type + ", Amount: $" + amount);
    }
}
