package lms;

public abstract class Item {
    private String itemID;
    private String name;
    private boolean isCheckkedOut = false;
    private double price;
    private int count;
    private boolean renewed = false; // Add this property

    // Getters and Setters
    public String getItemID() { return itemID; }
    public void setItemID(String itemID) { this.itemID = itemID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public boolean isRenewed() { return renewed; }
    public void setRenewed(boolean renewed) { this.renewed = renewed; }
     public boolean isCheckedOut() { return isCheckedout; }
    public void setRenewed(boolean renewed) { this.isCheckedOut = isCheckedOutd; }
}
