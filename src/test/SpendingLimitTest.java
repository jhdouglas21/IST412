package test;

import controller.NotificationController;
import model.SpendingLimit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.SpendingLimitPresenter;
import view.SpendingLimitView;

import static org.junit.jupiter.api.Assertions.*;

class SpendingLimitTest {

    private SpendingLimit spendingLimit;
    private TestSpendingLimitView view;
    private SpendingLimitPresenter presenter;
    private final String testUserId = "testUser";

    @BeforeEach
    void setUp() {
        NotificationController notificationController = NotificationController.getInstance();
        spendingLimit = new SpendingLimit(notificationController);
        view = new TestSpendingLimitView();
        presenter = new SpendingLimitPresenter(view, spendingLimit, testUserId);
    }

    // Unit Test 1
    @Test
    void testInvalidInputCharacter() {
        view.setLimitText("abc$");
        presenter.applySpendingLimit();
        assertEquals("Please enter a valid number.", view.getLastError());
    }

    // Unit Test 2
    @Test
    void testNegativeLimitDefaultsToZero() {
        view.setLimitText("-50");
        presenter.applySpendingLimit();
        assertEquals("Limit cannot be negative!", view.getLastError());
        assertEquals(0.0, spendingLimit.getSpendingLimit(testUserId));
    }

    // Non-Functional Test
    @Test
    void testCannotSetLimitBelowZero() {
        view.setLimitText("-9999");
        presenter.applySpendingLimit();
        double limit = spendingLimit.getSpendingLimit(testUserId);
        assertTrue(limit >= 0, "Spending limit must not be negative.");
    }

    private static class TestSpendingLimitView extends SpendingLimitView {
        private String limitText = "";
        private String lastError = "";

        @Override
        public double getLimitAmount() throws NumberFormatException {
            return Double.parseDouble(limitText);
        }

        @Override
        public void showError(String message) {
            lastError = message;
        }

        @Override
        public void showSuccess(String message) {
        }

        public void setLimitText(String text) {
            this.limitText = text;
        }

        public String getLastError() {
            return lastError;
        }
    }
}
