package lms;

import java.util.Date;

public class Fines {
    private String fineID;
    private String userID;
    private String itemID;
    private double amount;
    private Date dateIssued;

    // Getters and Setters
    public String getFineID() { return fineID; }
    public void setFineID(String fineID) { this.fineID = fineID; }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getItemID() { return itemID; }
    public void setItemID(String itemID) { this.itemID = itemID; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDateIssued() { return dateIssued; }
    public void setDateIssued(Date dateIssued) { this.dateIssued = dateIssued; }
}
