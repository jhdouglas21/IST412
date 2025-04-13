package view;

import model.SpendingLimit;

public class SpendingLimitPresenter {
    private SpendingLimitView view;
    private SpendingLimit model;
    private String userId;

    public SpendingLimitPresenter(SpendingLimitView view, SpendingLimit model, String userId) {
        this.view = view;
        this.model = model;
        this.userId = userId;

        init();
    }

    private void init() {
        view.setOnFinishAction(this::applySpendingLimit);
    }

    public void applySpendingLimit() {
        try {
            double limit = view.getLimitAmount();
            if (limit < 0) {
                view.showError("Limit cannot be negative!");
                return;
            }

            model.setSpendingLimit(userId, limit);
            view.showSuccess("Spending limit of $" + limit + " set successfully!");

        } catch (NumberFormatException e) {
            view.showError("Please enter a valid number.");
        }
    }
}
