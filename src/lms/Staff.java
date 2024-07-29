package lms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Staff extends User {
    // Methods
    public static void addItem(Scanner scanner, Library library, List<Item> items, List<Item> newItems) {
        System.out.println("Adding a new item...");
        System.out.println("Enter the type of item (b for Book / a for AudioVisual):");
        String type = scanner.nextLine().trim();

        if (type.equalsIgnoreCase("b")) {
            Books book = new Books();
            System.out.println("Enter Item ID:");
            book.setItemID(scanner.nextLine().trim());
            System.out.println("Enter Name:");
            book.setName(scanner.nextLine().trim());
            System.out.println("Enter Status (Available/Checked Out):");
            book.setStatus(scanner.nextLine().trim());
            System.out.println("Enter Price:");
            book.setPrice(Double.parseDouble(scanner.nextLine().trim()));
            System.out.println("Enter Author:");
            book.setAuthor(scanner.nextLine().trim());
            System.out.println("Enter ISBN:");
            book.setIsbn(scanner.nextLine().trim());
            System.out.println("Is it a BestSeller? (true/false):");
            book.setBestSeller(Boolean.parseBoolean(scanner.nextLine().trim()));
            items.add(book);
            newItems.add(book); // Track new item
        } else if (type.equalsIgnoreCase("a")) {
            AudioVisual av = new AudioVisual();
            System.out.println("Enter Item ID:");
            av.setItemID(scanner.nextLine().trim());
            System.out.println("Enter Name:");
            av.setName(scanner.nextLine().trim());
            System.out.println("Enter Status (Available/Checked Out):");
            av.setStatus(scanner.nextLine().trim());
            System.out.println("Enter Price:");
            av.setPrice(Double.parseDouble(scanner.nextLine().trim()));
            System.out.println("Enter Format (DVD/Blu-ray/Digital):");
            av.setFormat(scanner.nextLine().trim());
            items.add(av);
            newItems.add(av); // Track new item
        } else {
            System.out.println("Unknown item type. Please try again.");
        }
        library.setItems(items); // Update the library's item list
    }

    public static void addMember(Scanner scanner, Library library, List<Member> members, List<Member> newMembers) {
        Member member = new Member();
        System.out.println("Enter member name:");
        member.setName(scanner.nextLine().trim());
        System.out.println("Enter member address:");
        member.setAddress(scanner.nextLine().trim());
        System.out.println("Enter member phone number:");
        member.setPhoneNumber(scanner.nextLine().trim());
        System.out.println("Choose unique library card number:");
        member.setLibraryCardNumber(scanner.nextLine().trim());
        System.out.println("Enter member age:");
        member.setAge(Integer.parseInt(scanner.nextLine().trim()));
        member.setNumLoans(0);
        members.add(member);
        newMembers.add(member); // Track new item
        library.setMembers(members); // Update the library's item list
    }

}
