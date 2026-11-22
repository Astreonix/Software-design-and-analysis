package model;

public class OrderController {
    private int maxPaymentRetries;
    private int lastOrderId;
    private int activeOrderCount;
    private int timeoutSec;

    public OrderController() {
        maxPaymentRetries = 3;
        lastOrderId = 0;
        activeOrderCount = 0;
        timeoutSec = 30;
    }

    public Order placeOrder(Customer customer, Payment payment) {
        if (customer == null || payment == null) {
            return null;
        }
        Order order = customer.placeOrder(payment);
        if (order != null) {
            lastOrderId = order.getOrderId();
            activeOrderCount++;
        }
        return order;
    }

    public boolean processOrder(Order order, boolean accept) {
        if (order == null) {
            return false;
        }
        if (accept) {
            return order.updateStatus("Accepted");
        }
        return order.updateStatus("Rejected");
    }

    public boolean updateOrderStatus(Order order, String status) {
        return order != null && order.updateStatus(status);
    }

    public int getMaxPaymentRetries() { return maxPaymentRetries; }
    public void setMaxPaymentRetries(int maxPaymentRetries) { this.maxPaymentRetries = maxPaymentRetries; }
    public int getLastOrderId() { return lastOrderId; }
    public int getActiveOrderCount() { return activeOrderCount; }
    public int getTimeoutSec() { return timeoutSec; }
    public void setTimeoutSec(int timeoutSec) { this.timeoutSec = timeoutSec; }
}
