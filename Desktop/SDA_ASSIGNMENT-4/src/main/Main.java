package main;

import java.util.Scanner;
import model.CardPayment;
import model.Cart;
import model.CashOnDeliveryPayment;
import model.Customer;
import model.Delivery;
import model.DeliveryStaff;
import model.FoodItem;
import model.Menu;
import model.Order;
import model.OrderController;
import model.Payment;
import model.Restaurant;
import model.RestaurantStaff;
import model.User;
import model.WalletPayment;

public class Main {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Restaurant[] restaurants = createRestaurants();
        Customer customer = new Customer(101, "Mehr Ali", "mehr@example.com",
                "1234", "0300-0000000", "House 10, Lahore");
        RestaurantStaff staff = new RestaurantStaff(201, "Ali Raza", "staff@example.com",
                "1234", "RS-1", "Manager");
        DeliveryStaff deliveryStaff = new DeliveryStaff(301, "Usman", "delivery@example.com",
                "1234", "Bike", "Lahore");
        OrderController controller = new OrderController();
        Order[] orders = new Order[50];
        int orderCount = 0;

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    viewRestaurants(restaurants);
                    break;
                case 2:
                    manageCart(customer, restaurants);
                    break;
                case 3:
                    orderCount = placeOrder(customer, staff, deliveryStaff,
                            controller, orders, orderCount);
                    break;
                case 4:
                    trackOrder(customer, orders, orderCount);
                    break;
                case 5:
                    showPolymorphism(customer, staff, deliveryStaff);
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static Restaurant[] createRestaurants() {
        Restaurant[] restaurants = new Restaurant[2];
        restaurants[0] = new Restaurant(1, "Pizza Point", "Main Boulevard");
        restaurants[0].getMenu().addItem(new FoodItem(11, "Chicken Pizza", "Large pizza", 1200.0, true));
        restaurants[0].getMenu().addItem(new FoodItem(12, "Garlic Bread", 350.0));

        restaurants[1] = new Restaurant(2, "Burger Hub", "Gulberg");
        restaurants[1].getMenu().addItem(new FoodItem(21, "Zinger Burger", "Spicy chicken burger", 550.0, true));
        restaurants[1].getMenu().addItem(new FoodItem(22, "Cold Drink", 180.0));
        return restaurants;
    }

    private static void printMenu() {
        System.out.println("\n===== Food Ordering System =====");
        System.out.println("1. View restaurants and menus");
        System.out.println("2. Add item to cart");
        System.out.println("3. Place order and make payment");
        System.out.println("4. Track order");
        System.out.println("5. Demonstrate runtime polymorphism");
        System.out.println("0. Exit");
    }

    private static void viewRestaurants(Restaurant[] restaurants) {
        for (int i = 0; i < restaurants.length; i++) {
            Restaurant restaurant = restaurants[i];
            System.out.println("\nRestaurant " + restaurant.getRestaurantId() +
                    ": " + restaurant.getName() + " (" + restaurant.getAddress() + ")");
            FoodItem[] items = restaurant.getMenu().getAvailableItems();
            for (int j = 0; j < items.length; j++) {
                System.out.println("  " + items[j].getDetails());
            }
        }
    }

    private static void manageCart(Customer customer, Restaurant[] restaurants) {
        viewRestaurants(restaurants);
        int restaurantChoice = readInt("Select restaurant (1 or 2): ");
        if (restaurantChoice >= 1 && restaurantChoice <= restaurants.length) {
            Restaurant restaurant = restaurants[restaurantChoice - 1];
            int itemId = readInt("Enter item ID: ");
            FoodItem item = restaurant.getMenu().findItem(itemId);
            if (item != null && item.checkAvailability()) {
                int quantity = readInt("Enter quantity: ");
                customer.getCart().addItem(item, quantity);
                System.out.println("Cart updated. Total: Rs. " + customer.getCart().getTotalAmount());
            } else {
                System.out.println("Item is unavailable or not found.");
            }
        } else {
            System.out.println("Invalid restaurant.");
        }
    }

    private static int placeOrder(Customer customer, RestaurantStaff staff,
                                  DeliveryStaff deliveryStaff, OrderController controller,
                                  Order[] orders, int orderCount) {
        if (customer.getCart().isEmpty()) {
            System.out.println("Your cart is empty. Add an item first.");
            return orderCount;
        }

        System.out.println("1. Card  2. Wallet  3. Cash on Delivery");
        int paymentChoice = readInt("Select payment method: ");
        double amount = customer.getCart().getTotalAmount();
        Payment payment;

        if (paymentChoice == 1) {
            payment = new CardPayment(1001, customer.getName(), "1234");
        } else if (paymentChoice == 2) {
            payment = new WalletPayment(1002, "EasyPaisa", "03001234567");
        } else if (paymentChoice == 3) {
            payment = new CashOnDeliveryPayment(1003);
        } else {
            System.out.println("Invalid payment method.");
            return orderCount;
        }

        Order order = controller.placeOrder(customer, payment);
        if (order == null) {
            System.out.println("Payment failed or cart is empty.");
            return orderCount;
        }
        staff.acceptOrder(order);
        staff.updateOrderStatus(order, "Preparing");
        staff.updateOrderStatus(order, "Ready");

        Delivery delivery = new Delivery(order.getOrderId());
        delivery.assignTo(deliveryStaff);
        orders[orderCount++] = order;

        int roundedAmount = (int) (amount + 0.5);
        System.out.println("ORDER PLACED: " + order.getDetails());
        System.out.println("Payment status: " + payment.getStatus());
        System.out.println("Approximate rounded total: Rs. " + roundedAmount);
        System.out.println(delivery.getDetails());
        return orderCount;
    }

    private static void trackOrder(Customer customer, Order[] orders, int orderCount) {
        if (orderCount == 0) {
            System.out.println(customer.trackStatus());
            return;
        }
        for (int i = 0; i < orderCount; i++) {
            System.out.println(orders[i].getDetails());
        }
    }

    private static void showPolymorphism(Customer customer, RestaurantStaff staff,
                                         DeliveryStaff deliveryStaff) {
        System.out.println("\nRuntime polymorphism using User references:");
        User[] users = new User[3];
        users[0] = customer;
        users[1] = staff;
        users[2] = deliveryStaff;

        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i].getClass().getSimpleName() +
                    ": " + users[i].viewDashboard());
        }
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!input.hasNextInt()) {
            input.next();
            System.out.print("Enter a valid number: ");
        }
        int value = input.nextInt();
        input.nextLine();
        return value;
    }
}
