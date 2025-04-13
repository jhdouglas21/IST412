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

    //gets transaction id
    public String getTransactionId() {
        return transactionId;
    }


    // gets the transaction amount
    public double getAmount() {
        return amount;
    }

    // gets transaction type
    public String getType() {
        return type;
    }

    //gets timestamp
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // gets transaction status
    public String getStatus() {
        return status;
    }

    // updates status
    public void setStatus(String status) {
        this.status = status;
    }

    //validates transaction
    public boolean isValid() {
        return amount > 0 && (type.equalsIgnoreCase("deposit") || type.equalsIgnoreCase("bet") || type.equalsIgnoreCase("voucher"));
    }

    //returns details of transaction
    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
                "\nType: " + type +
                "\nAmount: $" + amount +
                "\nTimestamp: " + timestamp +
                "\nStatus: " + status;
    }
}
