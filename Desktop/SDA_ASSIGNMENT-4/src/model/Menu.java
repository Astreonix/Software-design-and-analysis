package model;

public class Menu {
    private int menuId;
    private String title;
    private boolean isActive;
    private FoodItem[] items;
    private int itemCount;

    public Menu(int menuId, String title) {
        this.menuId = menuId;
        this.title = title;
        this.isActive = true;
        this.items = new FoodItem[50];
        this.itemCount = 0;
    }

    public boolean addItem(FoodItem item) {
        if (item != null && itemCount < items.length) {
            items[itemCount++] = item;
            return true;
        }
        return false;
    }

    public boolean removeItem(int itemId) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getItemId() == itemId) {
                for (int j = i; j < itemCount - 1; j++) {
                    items[j] = items[j + 1];
                }
                items[itemCount - 1] = null;
                itemCount--;
                return true;
            }
        }
        return false;
    }

    public FoodItem findItem(int itemId) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getItemId() == itemId) {
                return items[i];
            }
        }
        return null;
    }

    public FoodItem[] getAvailableItems() {
        FoodItem[] available = new FoodItem[itemCount];
        int count = 0;
        for (int i = 0; i < itemCount; i++) {
            if (items[i].checkAvailability()) {
                available[count++] = items[i];
            }
        }
        FoodItem[] result = new FoodItem[count];
        for (int i = 0; i < count; i++) {
            result[i] = available[i];
        }
        return result;
    }

    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
