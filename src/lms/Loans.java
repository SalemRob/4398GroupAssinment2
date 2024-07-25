package lms;

import java.util.Date;

public class Loans {
    private String loanID;
    private Date dueDate;
    private String userID;
    private String itemID;
    private Date issueDate;
    private boolean renewed = false;
    private double itemPrice; // Store item price when loan is created

    // Getters and Setters
    public String getLoanID() { return loanID; }
    public void setLoanID(String loanID) { this.loanID = loanID; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getItemID() { return itemID; }
    public void setItemID(String itemID) { this.itemID = itemID; }

    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }

    public boolean isRenewed() { return renewed; }
    public void setRenewed(boolean renewed) { this.renewed = renewed; }

    public double getItemPrice() { return itemPrice; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }
}
