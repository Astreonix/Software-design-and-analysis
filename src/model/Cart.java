package model;

public class Cart {
    private int cartId;
    private double totalAmount;
    private String status;
    private CartItem[] items;
    private int itemCount;

    public Cart(int cartId) {
        this.cartId = cartId;
        this.status = "Active";
        this.items = new CartItem[20];
        this.itemCount = 0;
    }

    public Cart() {
        this(1);
    }

    public void addItem(FoodItem item, int quantity) {
        if (item != null && item.checkAvailability() && quantity > 0 && itemCount < items.length) {
            items[itemCount] = new CartItem(item, quantity);
            itemCount++;
            calculateTotal();
        }
    }

    public void removeItem(int itemId) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getFoodItem().getItemId() == itemId) {
                for (int j = i; j < itemCount - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[itemCount - 1] = null;
                itemCount--;
                calculateTotal();
                return;
            }
        }
    }

    public void updateQuantity(int itemId, int quantity) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getFoodItem().getItemId() == itemId) {
                items[i].changeQuantity(quantity);
                calculateTotal();
                return;
            }
        }
    }

    public CartItem[] getItems() {
        CartItem[] copy = new CartItem[itemCount];
        for (int i = 0; i < itemCount; i++) {
            copy[i] = items[i];
        }
        return copy;
    }

    public double calculateTotal() {
        return calculateTotal(items);
    }

    public static double calculateTotal(CartItem[] cartItems) {
        double total = 0.0;
        if (cartItems != null) {
            for (int i = 0; i < cartItems.length; i++) {
                if (cartItems[i] != null) {
                    total += cartItems[i].getSubtotal();
                }
            }
        }
        return total;
    }

    public boolean isEmpty() {
        return itemCount == 0;
    }

    public void clear() {
        items = new CartItem[20];
        itemCount = 0;
        totalAmount = 0.0;
        status = "Cleared";
    }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public double getTotalAmount() {
        totalAmount = calculateTotal(items);
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
