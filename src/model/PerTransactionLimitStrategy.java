package model;

public class PerTransactionLimitStrategy implements LimitStrategy {
    private double perTransactionCap;

    public PerTransactionLimitStrategy(double cap) {
        this.perTransactionCap = cap;
    }

    @Override
    public boolean isLimitExceeded(User user, double amount) {
        return amount > perTransactionCap;
    }
}
