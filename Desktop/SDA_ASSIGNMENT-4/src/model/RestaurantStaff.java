package model;

public class RestaurantStaff extends User {
    private String staffCode;
    private String designation;
    private String shift;
    private boolean onDuty;

    public RestaurantStaff(int userId, String name, String email, String passwordHash,
                           String staffCode, String designation) {
        super(userId, name, email, passwordHash);
        this.staffCode = staffCode;
        this.designation = designation;
        this.shift = "Day";
        this.onDuty = true;
        role = "RestaurantStaff";
    }

    public boolean acceptOrder(Order order) {
        if (order != null && onDuty) {
            order.updateStatus("Accepted");
            return true;
        }
        return false;
    }

    public boolean rejectOrder(Order order, String reason) {
        if (order != null && onDuty) {
            order.updateStatus("Rejected");
            order.setRejectionReason(reason);
            return true;
        }
        return false;
    }

    public boolean updateOrderStatus(Order order, String status) {
        if (order != null && onDuty) {
            order.updateStatus(status);
            return true;
        }
        return false;
    }

    @Override
    public String viewDashboard() {
        return "Restaurant Staff Dashboard: incoming orders and menu";
    }

    public String getStaffCode() { return staffCode; }
    public void setStaffCode(String staffCode) { this.staffCode = staffCode; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
    public boolean isOnDuty() { return onDuty; }
    public void setOnDuty(boolean onDuty) { this.onDuty = onDuty; }
}
