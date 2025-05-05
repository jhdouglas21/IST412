package model;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private double amount;
    private String type; // bet type like bet or deposit
    private LocalDateTime timestamp;
    private String status;

    public Transaction(String transactionId, double amount, String type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.status = "pending"; // Default status
    }

    public String getTransactionId() {
        return transactionId;
    }


    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isValid() {
        return amount > 0 && (type.equalsIgnoreCase("deposit") || type.equalsIgnoreCase("bet") || type.equalsIgnoreCase("voucher"));
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
                "\nType: " + type +
                "\nAmount: $" + amount +
                "\nTimestamp: " + timestamp +
                "\nStatus: " + status;
    }
}
