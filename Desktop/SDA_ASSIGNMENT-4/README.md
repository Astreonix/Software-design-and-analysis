# Food Ordering System - Assignment 4

**Student:** Mehr Ali  
**Registration Number:** 24P-0500  
**Course:** Software Design & Analysis

This Eclipse-compatible Java console application implements the Food Ordering System designed in Assignment 3.

## Classes

- `model.User`, `model.Customer`, `model.RestaurantStaff`, `model.DeliveryStaff`
- `model.Restaurant`, `model.Menu`, `model.FoodItem`
- `model.Cart`, `model.CartItem`
- `model.Order`, `model.Payment`, `model.CardPayment`, `model.WalletPayment`, `model.CashOnDeliveryPayment`
- `model.Delivery`, `model.Notification`, `model.OrderController`
- `model.Trackable`
- `main.Main`

## How to run

1. Open Eclipse IDE.
2. Choose **File > Open Projects from File System** and select this folder.
3. Confirm that `src` is a Java source folder.
4. Run `main.Main` as a Java Application.

From a terminal:

```bash
javac -d bin src/model/*.java src/main/*.java
java -cp bin main.Main
```
