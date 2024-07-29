package lms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Member extends User {
    private int numLoans;

    //getters and setters
    public int getNumLoans() { return numLoans; }
    public void setNumLoans(int newNumLoans) { this.numLoans = newNumLoans; }

    //Methods
    public static void checkoutItem(Scanner scanner, Library library, Member currentMember, List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers, List<Loans> loans, List<Loans> newLoans) {
        System.out.print("Enter item ID for checkout: ");
        String itemID = scanner.nextLine().trim().toUpperCase();

        Item item = Library.searchItems(itemID, 1);
        //check its a real ID
        if ( item != null ) {

            //check if checked out
            if (item.getStatus().equalsIgnoreCase("Checked Out")) {
                System.out.println("This book is currently checked out would you like to put in a request for it? Y/N");
                String response = scanner.nextLine().trim().toUpperCase();
                if (response.equals("Y")) {
                    item.setStatus("Requested");
                    return;
                } else {
                    System.out.println("Item is not available \n");
                    return;
                }
            }

            //check if child && >= 5 loans
            if (currentMember.getAge() <= 12 && currentMember.getNumLoans() >= 5) {
                System.out.println("A child (<=12) can only check out 5 books at a time");
                return;
            }
            //start checkout
            //change item status
            item.setStatus("Checked Out");
            //update numLoans for member
            currentMember.setNumLoans(currentMember.getNumLoans() + 1);
            //update loans list
            Loans loan = new Loans();
            loan.setItemID(itemID);
            loan.setUserID(currentMember.getLibraryCardNumber());
            LocalDate today = LocalDate.now();
            loan.setIssueDate(today);

            int loanTime = 2;
            if (item instanceof Books) { //to check if not a bestseller book
                Books book = (Books) item; // Cast to Books
                if (!book.isBestSeller()) {
                    loanTime = 3;
                }
            }
            loan.setDueDate(today.plus(loanTime, ChronoUnit.WEEKS));

            loans.add(loan);
            newLoans.add(loan);
            library.setLoans(loans);
            newItems.add(item);
            newMembers.add(currentMember);

        } else {
            System.out.println("Item not found \n");
            return;
        }
        System.out.print("You have checkout out the item \n");
        return;

}

    public static void returnItem(Scanner scanner, Library library, Member currentMember, List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers, List<Loans> loans, List<Loans> newLoans) {
        //check that user has item checked out
        System.out.print("Enter item ID for return: ");
        String itemID = scanner.nextLine().trim().toLowerCase();
        Loans loan = Library.searchLoans(itemID, 1);
        if (loan == null) {
            System.out.println("Loan not found \n");
            return;
        } else if (Library.searchLoans(currentMember.getLibraryCardNumber(),2) != loan) {
            System.out.println("Loan not under current user \n");
            return;
        }
        //change user numLoans
        currentMember.setNumLoans(currentMember.getNumLoans() - 1);
        //change item avalibility
        Item item = Library.searchItems(itemID, 1);
        item.setStatus("Available");
        //remove Loan
        loans.remove(loan);

        System.out.println("Item returned. \n");

    }

    public static void renewItem(Scanner scanner, Library library, Member currentMember, List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers, List<Loans> loans, List<Loans> newLoans) {
        //check that user has item checked out
        System.out.print("Enter item ID for renewal: ");
        String itemID = scanner.nextLine().trim().toLowerCase();
        Loans loan = Library.searchLoans(itemID, 1);
        if (loan == null) {
            System.out.println("Loan not found \n");
            return;
        } else if (!loan.getUserID().equals(currentMember.getLibraryCardNumber())) {
            System.out.println("Loan not under current user \n");
            return;
        }
        Item temp = Library.searchItems(itemID, 1);
        if (temp.getStatus().equalsIgnoreCase("Requested")) {
            System.out.println("Another user has requested this book \n");
            return;
        }
        //check if already renewed
        long currentIssueLength = java.time.Duration.between(loan.getIssueDate().atStartOfDay(), loan.getDueDate().atStartOfDay()).toDays();
        if (currentIssueLength > 21) {
            System.out.println("You cannot renew again \n");
            return;
        }
        //renew
        loan.setDueDate(loan.getDueDate().plus(3, ChronoUnit.WEEKS));

        System.out.println("Item Renewed. \n");
    }

    public static void viewFine(Scanner scanner, Library library, Member currentMember, List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers, List<Loans> loans, List<Loans> newLoans) {
        //check if user has anything checked out
        if (currentMember.getNumLoans() == 0) {
            System.out.println("No loans found \n");
            return;
        }
        double fineTotal = 0;
        for (Loans loan : loans) { //looks through all loans
            if (loan.getUserID().equals(currentMember.getLibraryCardNumber())) { //checks only those the currentMem has checked out
                if (loan.getDueDate().isBefore(LocalDate.now())) {
                    long daysOverdue = ChronoUnit.DAYS.between(loan.getDueDate(), LocalDate.now());
                    double fine = daysOverdue *0.01;
                    Item tempItem = Library.searchItems(loan.getItemID(),1);
                    if (tempItem.getPrice() < fine) { fine = tempItem.getPrice(); }
                    System.out.println("Fine of " + fine + " for itemID: " + loan.getItemID() +" \n");
                    fineTotal += fine;
                }
            }
        }
        System.out.println("Total Fines: $" + fineTotal + " \n");
    }

    public static void payFine(Scanner scanner, Library library, Member currentMember, List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers, List<Loans> loans, List<Loans> newLoans) {
        //check if user has anything checked out
        if (currentMember.getNumLoans() == 0) {
            System.out.println("No loans found \n");
            return;
        }
        List<Loans> loanToRemove = new ArrayList<>();
        double fineTotal = 0;
        for (Loans loan : loans) { //looks through all loans
            System.out.println("current loan: " + loan.getItemID() + " \n");
            if (loan.getUserID().equals(currentMember.getLibraryCardNumber())) { //checks only those the currentMem has checked out
                if (loan.getDueDate().isBefore(LocalDate.now())) {
                    long daysOverdue = ChronoUnit.DAYS.between(loan.getDueDate(), LocalDate.now());
                    double fine = daysOverdue *0.01;
                    Item tempItem = Library.searchItems(loan.getItemID(),1);
                    if (tempItem.getPrice() < fine) { fine = tempItem.getPrice(); }
                    fineTotal += fine;

                    currentMember.setNumLoans(currentMember.getNumLoans() - 1);
                    loanToRemove.add(loan);
                }
            }
        }
        loans.removeAll(loanToRemove);

        if (fineTotal ==0 ) { System.out.println("No fine found \n"); return; }
        System.out.println("Fines of $" + fineTotal + " Paid \n" );
    }

    public static void viewLoans(Scanner scanner, Library library, Member currentMember, List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers, List<Loans> loans, List<Loans> newLoans) {
        //check if user has anything checked out
        if (currentMember.getNumLoans() == 0) {
            System.out.println("No loans found \n");
            return;
        }
        System.out.println("Current Members Loans:");
        for (Loans loan : loans) { //looks through all loans
            if (loan.getUserID().equals(currentMember.getLibraryCardNumber())) { //checks only those the currentMem has checked out
                System.out.println("ItemID: " + loan.getItemID() + "  Issue Date: " + loan.getIssueDate() + "  Due Date: " + loan.getDueDate());
            }
        }
        System.out.println("\n");
    }
}
