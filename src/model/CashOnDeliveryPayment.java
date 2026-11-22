package model;

public class CashOnDeliveryPayment extends Payment {
    private boolean isCollected;
    private double amountCollected;
    private String collectedBy;

    public CashOnDeliveryPayment(int paymentId) {
        super(paymentId);
        this.isCollected = false;
        this.amountCollected = 0.0;
        this.collectedBy = "";
    }

    @Override
    public boolean process(double amount) {
        if (amount > 0) {
            setAmount(amount);
            setPaymentStatus("Pending");
            setTransactionRef("COD-" + paymentIdForRef());
            return true;
        }
        markFailed("Invalid COD amount");
        return false;
    }

    private int paymentIdForRef() {
        return getPaymentId();
    }

    public boolean confirmCollection(double amount) {
        if (amount >= getAmount()) {
            amountCollected = amount;
            isCollected = true;
            setPaymentStatus("Collected");
            return true;
        }
        return false;
    }

    public String getReceipt() {
        return "Cash receipt: " + getTransactionRef() + ", Rs. " + amountCollected;
    }

    public boolean isCollected() { return isCollected; }
    public double getAmountCollected() { return amountCollected; }
    public String getCollectedBy() { return collectedBy; }
    public void setCollectedBy(String collectedBy) { this.collectedBy = collectedBy; }
}
