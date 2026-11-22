package model;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String address;
    private boolean isOpen;
    private Menu menu;

    public Restaurant(int restaurantId, String name, String address) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.isOpen = true;
        this.menu = new Menu(restaurantId * 10, name + " Menu");
    }

    public Menu getMenu() {
        return menu;
    }

    public void receiveOrder(Order order) {
        if (order != null) {
            order.updateStatus("Accepted");
        }
    }

    public void openForOrders() {
        isOpen = true;
    }

    public void closeForOrders() {
        isOpen = false;
    }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean open) { isOpen = open; }
    public void setMenu(Menu menu) { this.menu = menu; }
}
