package lms;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String name;
    private String address;
    private String phoneNumber;
    private String libraryCardNumber;
    private int age;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getLibraryCardNumber() { return libraryCardNumber; }
    public void setLibraryCardNumber(String libraryCardNumber) { this.libraryCardNumber = libraryCardNumber; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}


