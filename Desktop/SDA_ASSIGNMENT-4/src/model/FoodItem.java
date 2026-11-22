package model;

public class FoodItem {
    private int itemId;
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;

    public FoodItem(int itemId, String name, double price) {
        this(itemId, name, "Fresh food item", price, true);
    }

    public FoodItem(int itemId, String name, String description, double price, boolean isAvailable) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public boolean checkAvailability() {
        return isAvailable;
    }

    public void setAvailability(boolean flag) {
        isAvailable = flag;
    }

    public void updatePrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public String getDetails() {
        return itemId + " - " + name + " - Rs. " + price +
                " - " + (isAvailable ? "Available" : "Unavailable");
    }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }
}
