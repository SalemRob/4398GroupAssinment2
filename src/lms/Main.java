package lms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize library and items
        Library library = new Library();
        List<Item> items = new ArrayList<>();

        // Read items from CSV
        List<List<String>> itemData = CSVUtil.readCSV("src/lms/items.csv"); // Adjusted file path
        // Skip header row
        boolean skipHeader = true;
        for (List<String> row : itemData) {
            if (skipHeader) {
                skipHeader = false;
                continue;
            }
            if (row.size() < 7) continue; // Ensure row has at least 7 columns
            String itemType = row.get(0);
            if ("Book".equalsIgnoreCase(itemType)) {
                Books book = new Books();
                book.setItemID(row.get(1));
                book.setName(row.get(2));
                book.setStatus(row.get(3));
                book.setPrice(Double.parseDouble(row.get(4)));
                book.setCount(Integer.parseInt(row.get(5)));
                book.setAuthor(row.get(6));
                book.setIsbn(row.get(7));
                book.setBestSeller(Boolean.parseBoolean(row.get(8)));
                items.add(book);
            } else if ("AudioVisual".equalsIgnoreCase(itemType)) {
                AudioVisual av = new AudioVisual();
                av.setItemID(row.get(1));
                av.setName(row.get(2));
                av.setStatus(row.get(3));
                av.setPrice(Double.parseDouble(row.get(4)));
                av.setCount(Integer.parseInt(row.get(5)));
                av.setFormat(row.get(6));
                items.add(av);
            }
        }
        library.setItems(items);

        // Read members from CSV
        List<Member> members = new ArrayList<>();
        List<List<String>> memberData = CSVUtil.readCSV("src/lms/members.csv"); // Adjusted file path
        // Skip header row
        skipHeader = true;
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
            members.add(member);
        }

        // Lists to track new items and members
        List<Item> newItems = new ArrayList<>();
        List<Member> newMembers = new ArrayList<>();

        // CLI
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("*************************  Welcome to the Library System  ************************");
            System.out.println("**********************************************************************************");
            // Login Prompt
            System.out.print("Please select your login type: \n (1) Staff \n (2) Member \n >>");
            String loginType = scanner.nextLine().trim();

            if (loginType.equals("1")) {
                // Staff Login
                System.out.print("Enter Staff Password:");
                String staffPassword = scanner.nextLine().trim();
                if (staffPassword.equals("1")) {
                    // Staff Menu
                    System.out.println("Staff Login Successful!");
                    staffMenu(scanner, library, items, newItems, members, newMembers);
                } else {
                    System.out.println("Invalid Staff Password. Exiting...");
                }
            } else if (loginType.equals("2")) {
                // Member Login
                memberLogin(scanner, library, members);
            } else if (loginType.equals("..")) {
                // Exit program
                System.out.println("Exiting the Library System. Goodbye!");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid selection. Exiting...");
            }
        }
    }

    private static void staffMenu(Scanner scanner, Library library, List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers) {
        while (true) {
            System.out.println("**********************************************************************************");
            System.out.println("Available commands: \n(1) add, \n(6) list, \n(7) listmembers, \n(m) main menu, \n(X) exit");
            System.out.println("**********************************************************************************");
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("..")) {
                break; // Return to main menu
            }

            switch (command) {
                case "1":
                    addItem(scanner, library, items, newItems);
                    break;

                case "6":
                    listItems(library);
                    break;

                case "7":
                    listMembers(members);
                    break;

                case "x":
                    exitProgram(scanner, library, newItems, newMembers);
                    return;

                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }
        }
    }

    private static void memberLogin(Scanner scanner, Library library, List<Member> members) {
        int attempts = 0;
        Member currentMember = null;
        while (attempts < 3 && currentMember == null) {
            System.out.println("Enter Member ID:");
            String memberId = scanner.nextLine().trim();
            if (memberId.equals("..")) {
                return; // Return to main menu
            }
            for (Member member : members) {
                if (member.getLibraryCardNumber().equals(memberId)) {
                    currentMember = member;
                    break;
                }
            }
            if (currentMember == null) {
                attempts++;
                System.out.println("Invalid Member ID. Attempts remaining: " + (3 - attempts));
            }
        }
        if (currentMember != null) {
            // Member Menu
            System.out.println("Member Login Successful!");
            memberMenu(scanner, library, currentMember);
        } else {
            System.out.println("Too many invalid attempts. Returning to main menu...");
        }
    }

    private static void memberMenu(Scanner scanner, Library library, Member currentMember) {
        while (true) {
            System.out.println("**********************************************************************************");
            System.out.println("Available commands: \n(2) checkout, \n(3) return, \n(4) renew, \n(5) payfine, \n(6) list items, \n(m) main menu, \n(X) exit");
            System.out.println("**********************************************************************************");
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("..")) {
                break; // Return to main menu
            }

            switch (command) {
                case "2":
                    checkoutItem(scanner, library, currentMember);
                    break;

                case "3":
                    returnItem(scanner, currentMember);
                    break;

                case "4":
                    renewItem(scanner, currentMember);
                    break;

                case "5":
                    payFine(scanner, currentMember);
                    break;

                case "6":
                    listItems(library);
                    break;

                case "x":
                    exitProgram(scanner, library, null, null);
                    return;

                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }
        }
    }

    private static void addItem(Scanner scanner, Library library, List<Item> items, List<Item> newItems) {
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
            System.out.println("Enter Count:");
            book.setCount(Integer.parseInt(scanner.nextLine().trim()));
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
            System.out.println("Enter Count:");
            av.setCount(Integer.parseInt(scanner.nextLine().trim()));
            System.out.println("Enter Format (DVD/Blu-ray/Digital):");
            av.setFormat(scanner.nextLine().trim());
            items.add(av);
            newItems.add(av); // Track new item
        } else {
            System.out.println("Unknown item type. Please try again.");
        }
        library.setItems(items); // Update the library's item list
    }

    private static void listItems(Library library) {
        System.out.println("Library Items:");
        for (Item item : library.getItems()) {
            System.out.println("Item ID: " + item.getItemID() + ", Name: " + item.getName() + ", Status: " + item.getStatus() + ", Price: " + item.getPrice() + ", Count: " + item.getCount());
        }
    }

    private static void listMembers(List<Member> members) {
        System.out.println("Library Members:");
        System.out.printf("%-20s %-50s %-15s %-5s %-15s\n", "Name", "Address", "Phone Number", "Age", "Card Number");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (Member member : members) {
            System.out.printf("%-20s %-50s %-15s %-5d %-15s\n",
                    member.getName(), member.getAddress(), member.getPhoneNumber(),
                    member.getAge(), member.getLibraryCardNumber());
        }
    }

    private static void checkoutItem(Scanner scanner, Library library, Member currentMember) {
        System.out.println("Enter item ID to checkout:");
        String itemId = scanner.nextLine();
        if (itemId.equals("..")) {
            return; // Return to previous menu
        }
        Item itemToCheckout = null;
        for (Item item : library.getItems()) {
            if (item.getItemID().equals(itemId)) {
                itemToCheckout = item;
                break;
            }
        }
        if (itemToCheckout != null) {
            if (currentMember.checkoutItem(itemToCheckout)) {
                System.out.println("Item checked out successfully.");
            } else {
                System.out.println("Failed to checkout item.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void returnItem(Scanner scanner, Member currentMember) {
        System.out.println("Enter item ID to return:");
        String returnItemId = scanner.nextLine();
        if (returnItemId.equals("..")) {
            return; // Return to previous menu
        }
        Item itemToReturn = null;
        for (Item item : currentMember.getCheckedOutItems()) {
            if (item.getItemID().equals(returnItemId)) {
                itemToReturn = item;
                break;
            }
        }
        if (itemToReturn != null) {
            if (currentMember.returnItem(itemToReturn)) {
                System.out.println("Item returned successfully.");
            } else {
                System.out.println("Failed to return item.");
            }
        } else {
            System.out.println("Item not found in checked out items.");
        }
    }

    private static void renewItem(Scanner scanner, Member currentMember) {
        System.out.println("Enter item ID to renew:");
        String renewItemId = scanner.nextLine();
        if (renewItemId.equals("..")) {
            return; // Return to previous menu
        }
        Item itemToRenew = null;
        for (Item item : currentMember.getCheckedOutItems()) {
            if (item.getItemID().equals(renewItemId)) {
                itemToRenew = item;
                break;
            }
        }
        if (itemToRenew != null) {
            if (!itemToRenew.isRenewed()) {
                itemToRenew.setRenewed(true);
                System.out.println("Item renewed successfully.");
            } else {
                System.out.println("Item has already been renewed once.");
            }
        } else {
            System.out.println("Item not found in checked out items.");
        }
    }

    private static void payFine(Scanner scanner, Member currentMember) {
        System.out.println("Enter amount to pay:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        // Add logic to pay fine
        // Example: currentMember.payFine(amount);
        System.out.println("Payment of " + amount + " processed.");
    }

    private static void exitProgram(Scanner scanner, Library library, List<Item> newItems, List<Member> newMembers) {
        System.out.println("Exiting the Library System. Goodbye!");
        scanner.close();

        if (newItems != null && newMembers != null) {
            // Write only new items to the CSV file
            List<List<String>> newItemData = new ArrayList<>();
            for (Item item : newItems) {
                List<String> row = new ArrayList<>();
                if (item instanceof Books) {
                    Books book = (Books) item;
                    row.add("Book");
                    row.add(book.getItemID());
                    row.add(book.getName());
                    row.add(book.getStatus());
                    row.add(String.valueOf(book.getPrice()));
                    row.add(String.valueOf(book.getCount()));
                    row.add(book.getAuthor());
                    row.add(book.getIsbn());
                    row.add(String.valueOf(book.isBestSeller()));
                    row.add("");
                } else if (item instanceof AudioVisual) {
                    AudioVisual av = (AudioVisual) item;
                    row.add("AudioVisual");
                    row.add(av.getItemID());
                    row.add(av.getName());
                    row.add(av.getStatus());
                    row.add(String.valueOf(av.getPrice()));
                    row.add(String.valueOf(av.getCount()));
                    row.add("");
                    row.add("");
                    row.add("");
                    row.add(av.getFormat());
                }
                newItemData.add(row);
            }
            CSVUtil.writeCSV("src/lms/items.csv", newItemData);

            // Write only new members to the CSV file
            List<List<String>> newMemberData = new ArrayList<>();
            for (Member member : newMembers) {
                List<String> row = new ArrayList<>();
                row.add(member.getName());
                row.add(member.getAddress());
                row.add(member.getPhoneNumber());
                row.add(String.valueOf(member.getAge()));
                row.add(member.getLibraryCardNumber());
                newMemberData.add(row);
            }
            CSVUtil.writeCSV("src/lms/members.csv", newMemberData);
        }
    }
}

