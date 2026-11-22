package model;

public class Order implements Trackable {
    private int orderId;
    private double totalAmount;
    private String status;
    private String deliveryAddress;
    private String rejectionReason;

    public Order(int orderId, double totalAmount, String deliveryAddress) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.status = "New";
        this.deliveryAddress = deliveryAddress;
        this.rejectionReason = "";
    }

    public boolean updateStatus(String status) {
        if (status != null && status.length() > 0) {
            this.status = status;
            return true;
        }
        return false;
    }

    public String getDetails() {
        return "Order #" + orderId + " | Status: " + status +
                " | Total: Rs. " + totalAmount + " | Address: " + deliveryAddress;
    }

    @Override
    public String trackStatus() {
        return "Order #" + orderId + ": " + status;
    }

    @Override
    public boolean markRead() {
        return true;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
}
