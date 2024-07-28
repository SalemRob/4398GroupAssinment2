package lms;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String name;
    private String address;
    private String phoneNumber;
    private String userID;
    private String libraryCardNumber;
    private int maxItem;
    private int age;
    private ArrayList <String[]> numOfBooksCheckedOut;
    private ArrayList <String[]> numOfAVCheckedOut;
    public User(String libraryCardNumber, String name, int age, String address, 
            String phoneNumber, int maxItems, ArrayList<String[]> numOfBooksCheckedOut, ArrayList<String[]> numOfAVCheckedOut) {
    this.name = name;
    this.age = age; // (?)
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.libraryCardNumber = libraryCardNumber;
    this.maxItem = maxItem
    this.numOfBooksCheckedOut = numOfBooksCheckedOut;
    this.numOfAVCheckedOut = numOfAVCheckedOut;
}

public int getNumberOfItems() {
    int numItems = numOfBooksCheckedOut.size() + numOfAVCheckedOut.size();
    return numItems;
}

public ArrayList<String[]> getCheckedOutBooks() {
    return numOfBooksCheckedOut;
}
public ArrayList<String[]> getAVCheckedOut() {
    return numOfAVCheckedOut;
}
public void returnBook(String[] item) {
	numOfBooksCheckedOut.remove(item);
}

public void returnAV(String[] item) {
	numOfAVCheckedOut.remove(item);
}

public ArrayList<String[]> getCheckedOutAV() {
    return numOfAVCheckedOut;
}

public String getName() {
    return name;
}

public int getAge() { // (?)
    return age;
}

public String getAddress() {
    return address;
}

public String getPhoneNumber() {
    return phoneNumber;
}

public String getLibraryCardNumber() {
    return libraryCardNumber;
}

public int getmaxItem() {
    return maxItem;
}

public void addBook(String title, String renewed, String dueDate) {
    String[] book = {title, renewed, dueDate};
    numOfBooksCheckedOut.add(book);
}


public void addAVItem(String title, String renewed, String dueDate) {
    String[] audioVisualItem = {title, renewed, dueDate};
    numOfAVCheckedOut.add(audioVisualItem);
}

public String toString() {
    String books = "";
    String av = "";
    for (String[] item : numOfBooksCheckedOut) {
        books += "Title: " + item[0] + " Checkout Date: " + item[1] + " Due Date: " + item[2] + "\n";
    } 
    for (String[] item : numOfAVCheckedOut) {
        av += "Title: " + item[0] + " Checkout Date: " + item[1] + " Due Date: " + item[2] + "\n";
    }

    return ("Name: " + name + "\n" +
            "Library Card Number: " + libraryCardNumber + "\n" +
            "Age: " + age + "\n" +
            "Address: " + address + "\n" +
            "Phone number: " + phoneNumber + "\n" +
            "Max Items: " + maxItem + "\n" +
            books + av);

}
    

   
}

