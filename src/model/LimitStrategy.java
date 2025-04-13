package model;

public interface LimitStrategy {
    boolean isLimitExceeded(User user, double amount);
}
