import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String name;
    private String address;
    private String phoneNumber;
    private String libraryCardNumber;
    private double fineAmount;

    public User(String name, String address, String phoneNumber, String libraryCardNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.libraryCardNumber = libraryCardNumber;
        this.fineAmount = 0.0;
    }

    public String getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void addFine(double amount) {
        this.fineAmount += amount;
    }

    public void payFine(double amount) {
        this.fineAmount -= amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", libraryCardNumber='" + libraryCardNumber + '\'' +
                ", fineAmount=" + fineAmount +
                '}';
    }

    public abstract void sendToLoans(Item item, LocalDate dueDate);
}

class Member extends User {
    private List<Loan> loans;

    public Member(String name, String address, String phoneNumber, String libraryCardNumber) {
        super(name, address, phoneNumber, libraryCardNumber);
        this.loans = new ArrayList<>();
    }

    public List<Loan> getLoans() {
        return loans;
    }

    @Override
    public void sendToLoans(Item item, LocalDate dueDate) {
        loans.add(new Loan(this, item, dueDate));
    }
}

class Staff extends User {
    public Staff(String name, String address, String phoneNumber, String libraryCardNumber) {
        super(name, address, phoneNumber, libraryCardNumber);
    }

    @Override
    public void sendToLoans(Item item, LocalDate dueDate) {
        System.out.println("Staff members cannot check out items.");
    }
}
