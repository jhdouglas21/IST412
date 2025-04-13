package model;

public class LimitEnforcer {
    private LimitStrategy strategy;

    public void setStrategy(LimitStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean enforce(User user, double amount) {
        return strategy.isLimitExceeded(user, amount);
    }
}
