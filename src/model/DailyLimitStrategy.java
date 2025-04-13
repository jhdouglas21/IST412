package model;

public class DailyLimitStrategy implements LimitStrategy {
    private SpendingLimit model;

    public DailyLimitStrategy(SpendingLimit model) {
        this.model = model;
    }

    @Override
    public boolean isLimitExceeded(User user, double amount) {
        double spent = model.getUserSpending(user.getUserId());
        double limit = model.getSpendingLimit(user.getUserId());
        return (spent + amount) > limit;
    }
}
