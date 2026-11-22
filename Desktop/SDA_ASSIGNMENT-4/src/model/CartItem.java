package model;

public class CartItem {
    private int quantity;
    private double unitPrice;
    private double subtotal;
    private String note;
    private FoodItem foodItem;

    public CartItem(FoodItem foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.unitPrice = foodItem.getPrice();
        this.note = "";
        refreshSubtotal();
    }

    public void changeQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
            refreshSubtotal();
        }
    }

    public double getSubtotal() {
        refreshSubtotal();
        return subtotal;
    }

    public void refreshPrice(double price) {
        if (price >= 0) {
            unitPrice = price;
            refreshSubtotal();
        }
    }

    private void refreshSubtotal() {
        subtotal = unitPrice * quantity;
    }

    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public FoodItem getFoodItem() { return foodItem; }
}
