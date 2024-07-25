package lms;

public class Item {
    private String itemID;
    private String name;
    private String status;
    private double price;
    private int count;
    private int checkoutLength; // in days

    // Getters and Setters
    public String getItemID() { return itemID; }
    public void setItemID(String itemID) { this.itemID = itemID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public int getCheckoutLength() { return checkoutLength; }
    public void setCheckoutLength(int checkoutLength) { this.checkoutLength = checkoutLength; }
}
