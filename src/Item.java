public class Item {
    private String itemID;
    private String name;
    private int checkoutLength;
    private String status;
    private double price;
    private int count;

    // Getters and Setters
    public String getItemID() { return itemID; }
    public void setItemID(String itemID) { this.itemID = itemID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCheckoutLength() { return checkoutLength; }
    public void setCheckoutLength(int checkoutLength) { this.checkoutLength = checkoutLength; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}

public class Books extends Item {
    private String author;
    private String isbn;
    private boolean bestSeller;

    // Getters and Setters
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public boolean isBestSeller() { return bestSeller; }
    public void setBestSeller(boolean bestSeller) { this.bestSeller = bestSeller; }
}
public class AudioVisual extends Item {
    private String format;

    // Getters and Setters
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
}
public class MagazineReferenceBooks extends Item {
    private String format;

    // Getters and Setters
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
}