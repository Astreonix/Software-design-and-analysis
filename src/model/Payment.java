package model;

public abstract class Payment {
    private int paymentId;
    private double amount;
    private String paymentStatus;
    private String transactionRef;

    public Payment(int paymentId) {
        this.paymentId = paymentId;
        this.amount = 0.0;
        this.paymentStatus = "Pending";
        this.transactionRef = "";
    }

    public abstract boolean process(double amount);

    public String getStatus() {
        return paymentStatus;
    }

    public void markFailed(String reason) {
        paymentStatus = "Failed";
        transactionRef = reason;
    }

    public boolean refund() {
        if ("Paid".equals(paymentStatus) || "Collected".equals(paymentStatus)) {
            paymentStatus = "Refunded";
            return true;
        }
        return false;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    protected void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getTransactionRef() { return transactionRef; }
    public void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }
}
