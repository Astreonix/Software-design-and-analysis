package model;

public class Notification {
    private int notificationId;
    private String message;
    private String type;
    private boolean isRead;

    public Notification(int notificationId, String type) {
        this.notificationId = notificationId;
        this.type = type;
        this.message = "";
        this.isRead = false;
    }

    public boolean send(User user, String message) {
        if (user == null || message == null || message.length() == 0) {
            return false;
        }
        this.message = message;
        isRead = false;
        return true;
    }

    public void markRead() {
        isRead = true;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public int getNotificationId() { return notificationId; }
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public boolean isRead() { return isRead; }
}
