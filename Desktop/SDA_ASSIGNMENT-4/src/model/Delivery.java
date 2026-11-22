package model;

public class Delivery {
    private int deliveryId;
    private String status;
    private DeliveryStaff assignedStaff;
    private boolean pickedUp;

    public Delivery(int deliveryId) {
        this.deliveryId = deliveryId;
        this.status = "Pending";
        this.assignedStaff = null;
        this.pickedUp = false;
    }

    public boolean assignTo(DeliveryStaff staff) {
        if (staff != null && staff.isAvailable()) {
            assignedStaff = staff;
            status = "Assigned";
            return true;
        }
        return false;
    }

    public boolean updateStatus(String status) {
        if (status == null || status.length() == 0) {
            return false;
        }
        this.status = status;
        if ("PickedUp".equals(status)) {
            pickedUp = true;
        }
        return true;
    }

    public String getDetails() {
        return "Delivery #" + deliveryId + " - " + status +
                (assignedStaff == null ? " - unassigned" : " - " + assignedStaff.getName());
    }

    public int getDeliveryId() { return deliveryId; }
    public void setDeliveryId(int deliveryId) { this.deliveryId = deliveryId; }
    public String getStatus() { return status; }
    public DeliveryStaff getAssignedStaff() { return assignedStaff; }
    public boolean isPickedUp() { return pickedUp; }
}
