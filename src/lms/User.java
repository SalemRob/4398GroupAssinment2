package lms;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String name;
    private String address;
    private String phoneNumber;
    private String userID;
    private String libraryCardNumber;
    private int age;
    private List<Loans> loans = new ArrayList<>();
    private List<Fines> fines = new ArrayList<>();

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getLibraryCardNumber() { return libraryCardNumber; }
    public void setLibraryCardNumber(String libraryCardNumber) { this.libraryCardNumber = libraryCardNumber; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public List<Loans> getLoans() { return loans; }
    public void setLoans(List<Loans> loans) { this.loans = loans; }

    public List<Fines> getFines() { return fines; }
    public void setFines(List<Fines> fines) { this.fines = fines; }
}
