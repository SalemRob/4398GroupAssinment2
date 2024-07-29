package lms;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Library {
    private static List<Item> items = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static List<Loans> loans = new ArrayList<>();

    // Getters and Setters
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public List<Member> getMembers() { return members; }
    public void setMembers(List<Member> members) { this.members = members; }

    public List<Loans> getLoans() { return loans; }
    public void setLoans(List<Loans> loans) { this.loans = loans; }

    //Methods

    //initialization
    public static List<Item> initializeItems() {
        List<List<String>> itemData = CSVUtil.readCSV("src/lms/items.csv"); // Adjusted file path
        // Skip header row
        boolean skipHeader = true;
        for (List<String> row : itemData) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }
            if (row.size() < 6) continue; // Ensure row has at least 7 columns
            String itemType = row.get(0);
            if ("Book".equalsIgnoreCase(itemType)) {
                Books book = new Books();
                book.setItemID(row.get(1));
                book.setName(row.get(2));
                book.setStatus(row.get(3));
                book.setPrice(Double.parseDouble(row.get(4)));
                book.setAuthor(row.get(5));
                book.setIsbn(row.get(6));
                book.setBestSeller(Boolean.parseBoolean(row.get(7)));
                items.add(book);
            } else if ("AudioVisual".equalsIgnoreCase(itemType)) {
                AudioVisual av = new AudioVisual();
                av.setItemID(row.get(1));
                av.setName(row.get(2));
                av.setStatus(row.get(3));
                av.setPrice(Double.parseDouble(row.get(4)));
                av.setFormat(row.get(5));
                items.add(av);
            }
        }
        return items;
    }

    public static List<Member> initializeMembers() {
        List<List<String>> memberData = CSVUtil.readCSV("src/lms/members.csv"); // Adjusted file path
        boolean skipHeader = true;
        for (List<String> row : memberData) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }
            if (row.size() < 5) continue; // Skip rows with insufficient data
            Member member = new Member();
            member.setName(row.get(0));
            member.setAddress(row.get(1));
            member.setPhoneNumber(row.get(2));
            member.setAge(Integer.parseInt(row.get(3)));
            member.setLibraryCardNumber(row.get(4));
            member.setNumLoans(Integer.parseInt(row.get(5)));
            members.add(member);
        }
        return members;
    }

    public static List<Loans> initializeLoans() {
        List<List<String>> loanData = CSVUtil.readCSV("src/lms/loans.csv"); // Adjusted file path
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE; // Define the date format

        // Skip header row
        boolean skipHeader = true;
        for (List<String> row : loanData) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }
            if (row.size() < 3) continue; // Ensure row has at least 4 columns
            Loans loan = new Loans();
            loan.setItemID(row.get(0));
            loan.setUserID(row.get(1));
            try {
                loan.setIssueDate(LocalDate.parse(row.get(2), formatter));
                loan.setDueDate(LocalDate.parse(row.get(3), formatter));
            } catch (Exception e) {
                System.out.println("Error parsing dates: " + e.getMessage());
                }
            loans.add(loan);
        }
        return loans;
    }

    //display lists
    public static void listItems(Library library) {
        System.out.println("Library Items:");
        for (Item item : library.getItems()) {
            System.out.println("Item ID: " + item.getItemID() + ", Name: " + item.getName() + ", Status: " + item.getStatus() + ", Price: " + item.getPrice());
        }
    }

    public static void listMembers(List<Member> members) {
        System.out.println("Library Members:");
        System.out.printf("%-20s %-50s %-15s %-5s %-15s %-10s\n", "Name", "Address", "Phone Number", "Age", "Card Number", "Number of Loans");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        for (Member member : members) {
            System.out.printf("%-20s %-50s %-15s %-5d %-15s %-10d\n",
                    member.getName(), member.getAddress(), member.getPhoneNumber(),
                    member.getAge(), member.getLibraryCardNumber(), member.getNumLoans());
        }
    }

    public static void listLoans(Library library) {
        System.out.println("Library Loans:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Loans loan : library.getLoans()) {
            String issueDate = loan.getIssueDate().format(formatter);
            String dueDate = loan.getDueDate().format(formatter);
            System.out.println("Item ID: " + loan.getItemID() + ", Member ID: " + loan.getUserID() + ", Issue date: " + loan.getIssueDate() + ", Due date: " + loan.getDueDate());
        }
    }

    //search lists and returns class instance found, null otherwise
    public static Item searchItems(String searchTerm, int row) {
        for (Item item : items) {
            switch (row) {
                case 1: // Search by Item ID
                    if (item.getItemID().equalsIgnoreCase(searchTerm)) {
                        return item;
                    }
                    break;
                case 2: // Search by Name
                    if (item.getName().equalsIgnoreCase(searchTerm)) {
                        return item;
                    }
                    break;
                case 3: // Search by Status
                    if (item.getStatus().equalsIgnoreCase(searchTerm)) {
                        return item;
                    }
                    break;
                case 4: // Search by Price
                    try {
                        double searchPrice = Double.parseDouble(searchTerm);
                        if (item.getPrice() == searchPrice) {
                            return item;
                        }
                    } catch (NumberFormatException e) {
                        // Handle case where searchTerm is not a valid number
                    }
                    break;
                // Add additional cases for other attributes if needed
                default:
                    System.out.println("Invalid row number.");
                    return null;
            }
        }
        return null;
    }

    public static Member searchMembers(String searchTerm, int row) {
        for (Member member : members) {
            switch (row) {
                case 1: // Search by Name
                    if (member.getName().equalsIgnoreCase(searchTerm)) {
                        return member;
                    }
                    break;
                case 2: // Search by Address
                    if (member.getAddress().equalsIgnoreCase(searchTerm)) {
                        return member;
                    }
                    break;
                case 3: // Search by Phone Number
                    if (member.getPhoneNumber().equalsIgnoreCase(searchTerm)) {
                        return member;
                    }
                    break;
                case 4: // Search by Age
                    try {
                        int searchAge = Integer.parseInt(searchTerm);
                        if (member.getAge() == searchAge) {
                            return member;
                        }
                    } catch (NumberFormatException e) {
                        // Handle case where searchTerm is not a valid number
                    }
                    break;
                case 5: // Search by Library Card Number
                    if (member.getLibraryCardNumber().equalsIgnoreCase(searchTerm)) {
                        return member;
                    }
                    break;
                default:
                    System.out.println("Invalid row number.");
                    return null;
            }
        }
        return null;
    }

    public static Loans searchLoans(String searchTerm, int row) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE; // Use ISO format for comparison

        for (Loans loan : loans) {
            switch (row) {
                case 1: // Search by Item ID
                    if (loan.getItemID().equalsIgnoreCase(searchTerm)) {
                        return loan;
                    }
                    break;
                case 2: // Search by Member Card Number
                    if (loan.getUserID().equalsIgnoreCase(searchTerm)) {
                        return loan;
                    }
                    break;
                case 3: // Search by Issue Date
                    if (loan.getIssueDate().format(formatter).equalsIgnoreCase(searchTerm)) {
                        return loan;
                    }
                    break;
                case 4: // Search by Due Date
                    if (loan.getDueDate().format(formatter).equalsIgnoreCase(searchTerm)) {
                        return loan;
                    }
                    break;
                default:
                    System.out.println("Invalid row number.");
                    return null;
            }
        }
        return null;
    }
}
