package model;

public class Customer extends User implements Trackable {
    private String phone;
    private String deliveryAddress;
    private boolean isVerified;
    private Cart cart;
    private Order[] orders;
    private int orderCount;

    public Customer(int userId, String name, String email, String passwordHash,
                    String phone, String deliveryAddress) {
        super(userId, name, email, passwordHash);
        this.phone = phone;
        this.deliveryAddress = deliveryAddress;
        this.isVerified = false;
        this.cart = new Cart(userId);
        this.orders = new Order[50];
        this.orderCount = 0;
        role = "Customer";
    }

    public Customer(String name, String email) {
        super(name, email);
        this.phone = "";
        this.deliveryAddress = "";
        this.isVerified = false;
        this.cart = new Cart();
        this.orders = new Order[50];
        this.orderCount = 0;
        role = "Customer";
    }

    public void browseRestaurants(Restaurant[] restaurants) {
        for (int i = 0; i < restaurants.length; i++) {
            if (restaurants[i] != null) {
                System.out.println(restaurants[i].getRestaurantId() + ". " + restaurants[i].getName());
            }
        }
    }

    public Menu viewMenu(Restaurant restaurant) {
        return restaurant == null ? null : restaurant.getMenu();
    }

    public Order placeOrder(Payment payment) {
        if (cart.isEmpty()) {
            return null;
        }
        Order order = new Order(++orderCount, cart.getTotalAmount(), deliveryAddress);
        if (payment != null && payment.process(cart.getTotalAmount())) {
            order.updateStatus("Preparing");
            if (orderCount < orders.length) {
                orders[orderCount - 1] = order;
            }
            cart.clear();
            return order;
        }
        return null;
    }

    @Override
    public String viewDashboard() {
        return "Customer Dashboard: browse restaurants, manage cart and track orders";
    }

    @Override
    public String trackStatus() {
        if (orderCount == 0) {
            return "No orders placed";
        }
        return "Latest order status: " + orders[orderCount - 1].getStatus();
    }

    @Override
    public boolean markRead() {
        return true;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }
    public Cart getCart() { return cart; }
    public void setCart(Cart cart) { this.cart = cart; }
}
