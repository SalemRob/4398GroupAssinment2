package lms;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class User {
    private String name;
    private String userID;
    private String address;
    private String phoneNumber;
    private String libraryCardNumber;
    private List<Loans> loans = new ArrayList<>();
    private List<Fines> fines = new ArrayList<>();

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getLibraryCardNumber() { return libraryCardNumber; }
    public void setLibraryCardNumber(String libraryCardNumber) { this.libraryCardNumber = libraryCardNumber; }

    public List<Loans> getLoans() { return loans; }
    public void setLoans(List<Loans> loans) { this.loans = loans; }

    public List<Fines> getFines() { return fines; }
    public void setFines(List<Fines> fines) { this.fines = fines; }

    public void register() {
        // Registration logic
    }

    public void updateProfile() {
        // Profile update logic
    }

    // Add method to check out an item
    public void checkoutItem(Item item) {
        Loans loan = new Loans();
        loan.setItemID(item.getItemID());
        loan.setUserID(this.getUserID());
        loan.setIssueDate(new Date());
        loan.setDueDate(calculateDueDate(loan.getIssueDate(), item.getCheckoutLength()));
        loan.setItemPrice(item.getPrice()); // Set the item price when creating the loan
        this.getLoans().add(loan);
    }


    private java.util.Date calculateDueDate(java.util.Date issueDate, int checkoutLength) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(issueDate);
        calendar.add(java.util.Calendar.DAY_OF_MONTH, checkoutLength);
        return calendar.getTime();
    }

    public void returnItem(String itemID) {
        // Return item logic
    }

    public void renewItem(String itemID) {
        // Renew item logic
    }

    public void payFine(double amount) {
        // Pay fine logic
    }
}
