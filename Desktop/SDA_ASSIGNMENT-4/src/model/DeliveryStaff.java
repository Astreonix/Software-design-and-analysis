package model;

public class DeliveryStaff extends User {
    private String licenseNo;
    private String vehicleType;
    private boolean isAvailable;
    private String currentArea;

    public DeliveryStaff(int userId, String name, String email, String passwordHash,
                         String vehicleType, String currentArea) {
        super(userId, name, email, passwordHash);
        this.licenseNo = "";
        this.vehicleType = vehicleType;
        this.isAvailable = true;
        this.currentArea = currentArea;
        role = "DeliveryStaff";
    }

    public Delivery[] viewAssignedDeliveries(Delivery[] deliveries) {
        int count = 0;
        for (int i = 0; i < deliveries.length; i++) {
            if (deliveries[i] != null &&
                    deliveries[i].getAssignedStaff() == this) {
                count++;
            }
        }
        Delivery[] result = new Delivery[count];
        int index = 0;
        for (int i = 0; i < deliveries.length; i++) {
            if (deliveries[i] != null &&
                    deliveries[i].getAssignedStaff() == this) {
                result[index++] = deliveries[i];
            }
        }
        return result;
    }

    public boolean updateDeliveryStatus(Delivery delivery, String status) {
        if (delivery != null && isAvailable) {
            delivery.updateStatus(status);
            return true;
        }
        return false;
    }

    public void setAvailability(boolean available) {
        isAvailable = available;
    }

    @Override
    public String viewDashboard() {
        return "Delivery Dashboard: assigned deliveries and status updates";
    }

    public String getLicenseNo() { return licenseNo; }
    public void setLicenseNo(String licenseNo) { this.licenseNo = licenseNo; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public boolean isAvailable() { return isAvailable; }
    public String getCurrentArea() { return currentArea; }
    public void setCurrentArea(String currentArea) { this.currentArea = currentArea; }
}
