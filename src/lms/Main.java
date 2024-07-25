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
                av.setFormat(row.get(9));
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

        // CLI
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("\n");
            System.out.println("*************************  Welcome to the Library System  ************************");
            System.out.println("Please select your login type: \n (1) Staff Member \n (2) Library Member");
            System.out.println("**********************************************************************************");
            // Login Prompt

            String loginType = scanner.nextLine().trim();

            if (loginType.equals("1")) {
                // Staff Login
                System.out.println("Enter Staff Password: ");
                String staffPassword = scanner.nextLine();

                if (staffPassword.equals("1111")) {
                    // Staff Menu
                    System.out.println("Staff Login Successful!");
                    System.out.println("**********************************************************************************");
                    System.out.println("Available commands: \n(1) add item \n(6) list or items " +
                            "\n(7) list of members \n(X) exit system");
                    System.out.println("**********************************************************************************");

                    while (true) {
                        System.out.print(">>> ");
                        command = scanner.nextLine().trim().toLowerCase();

                        switch (command) {
                            case "1":
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
                                } else {
                                    System.out.println("Unknown item type. Please try again.");
                                }
                                library.setItems(items); // Update the library's item list
                                break;

                            case "6":
                                System.out.println("Library Items:");
                                for (Item item : library.getItems()) {
                                    System.out.println("Item ID: " + item.getItemID() + ", Name: " + item.getName() + ", Status: " + item.getStatus() + ", Price: " + item.getPrice() + ", Count: " + item.getCount());
                                }
                                break;

                            case "7":
                                System.out.println("Library Members:");
                                System.out.printf("%-20s %-50s %-15s %-5s %-15s\n", "Name", "Address", "Phone Number", "Age", "Card Number");
                                System.out.println("-----------------------------------------------------------------------------------------------------------");
                                for (Member member : members) {
                                    System.out.printf("%-20s %-50s %-15s %-5d %-15s\n",
                                            member.getName(), member.getAddress(), member.getPhoneNumber(),
                                            member.getAge(), member.getLibraryCardNumber());
                                }
                                break;

                            case "x":
                                System.out.println("Exiting the Library System. Goodbye!");
                                scanner.close();

                                // Read the existing data from the CSV files
                                List<List<String>> existingItemData = CSVUtil.readCSV("src/lms/items.csv");
                                List<List<String>> existingMemberData = CSVUtil.readCSV("src/lms/members.csv");

                                // Combine the existing data with the new data
                                for (Item item : library.getItems()) {
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
                                    existingItemData.add(row);
                                }

                                // Write the combined data back to the CSV files
                                CSVUtil.writeCSV("src/lms/items.csv", existingItemData);

                                // Combine the existing member data with any new members
                                for (Member member : members) {
                                    List<String> row = new ArrayList<>();
                                    row.add(member.getName());
                                    row.add(member.getAddress());
                                    row.add(member.getPhoneNumber());
                                    row.add(String.valueOf(member.getAge()));
                                    row.add(member.getUserID());
                                    row.add(member.getLibraryCardNumber());
                                    existingMemberData.add(row);
                                }

                                CSVUtil.writeCSV("src/lms/members.csv", existingMemberData);
                                return;

                            default:
                                System.out.println("Unknown command. Please try again.");
                                break;
                        }
                    }
                } else {
                    System.out.println("Invalid Staff Password. Exiting...");
                }
            } else if (loginType.equals("2")) {
                // Member Login
                int attempts = 0;
                Member currentMember = null;
                while (attempts < 3 && currentMember == null) {
                    System.out.println("Enter Member ID:");
                    String memberId = scanner.nextLine().trim();
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
                    System.out.println("**********************************************************************************");
                    System.out.println("Available commands: \n(2) checkout, \n(3) return, \n(4) renew, \n(5) payfine, \n(6) list items, \n(X) exit");
                    System.out.println("**********************************************************************************");

                    while (true) {
                        System.out.print("> ");
                        command = scanner.nextLine().trim().toLowerCase();

                        switch (command) {
                            case "2":
                                System.out.println("Enter item ID to checkout:");
                                String itemId = scanner.nextLine();
                                // Add logic to checkout item
                                // Example: currentMember.checkoutItem(item);
                                break;

                            case "3":
                                System.out.println("Enter item ID to return:");
                                String returnItemId = scanner.nextLine();
                                // Add logic to return item
                                // Example: currentMember.returnItem(returnItemId);
                                break;

                            case "4":
                                System.out.println("Enter item ID to renew:");
                                String renewItemId = scanner.nextLine();
                                // Add logic to renew item
                                // Example: currentMember.renewItem(renewItemId);
                                break;

                            case "5":
                                System.out.println("Enter amount to pay:");
                                double amount = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline
                                // Add logic to pay fine
                                // Example: currentMember.payFine(amount);
                                break;

                            case "6":
                                System.out.println("Library Items:");
                                for (Item item : library.getItems()) {
                                    System.out.println("Item ID: " + item.getItemID() + ", Name: " + item.getName() + ", Status: " + item.getStatus() + ", Price: " + item.getPrice() + ", Count: " + item.getCount());
                                }
                                break;

                            case "x":
                                System.out.println("Exiting the Library System. Goodbye!");
                                scanner.close();

                                // Read the existing data from the CSV files
                                List<List<String>> existingItemData = CSVUtil.readCSV("src/lms/items.csv");
                                List<List<String>> existingMemberData = CSVUtil.readCSV("src/lms/members.csv");

                                // Combine the existing data with the new data
                                for (Item item : library.getItems()) {
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
                                    existingItemData.add(row);
                                }

                                // Write the combined data back to the CSV files
                                CSVUtil.writeCSV("src/lms/items.csv", existingItemData);

                                // Combine the existing member data with any new members
                                for (Member member : members) {
                                    List<String> row = new ArrayList<>();
                                    row.add(member.getName());
                                    row.add(member.getAddress());
                                    row.add(member.getPhoneNumber());
                                    row.add(String.valueOf(member.getAge()));
                                    row.add(member.getUserID());
                                    row.add(member.getLibraryCardNumber());
                                    existingMemberData.add(row);
                                }

                                CSVUtil.writeCSV("src/lms/members.csv", existingMemberData);
                                return;

                            default:
                                System.out.println("Unknown command. Please try again.");
                                break;
                        }
                    }
                } else {
                    System.out.println("Too many invalid attempts. Returning to main menu...");
                }
            } else {
                System.out.println("Invalid selection. Exiting...");
            }
        }
    }
}
