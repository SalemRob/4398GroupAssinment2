package lms;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Initialize library, items, members, and loans
        Library library = new Library();
        List<Item> items = Library.initializeItems();
        List<Member> members = Library.initializeMembers();
        List<Loans> loans = Library.initializeLoans();

        // Lists to track new items and members
        List<Item> newItems = new ArrayList<>();
        List<Member> newMembers = new ArrayList<>();
        List<Loans> newLoans = new ArrayList<>();

        // CLI
        Scanner scanner = new Scanner(System.in);

        //basic run loop
        while (true) {
            System.out.println("*************************  Welcome to the Library System  ************************");
            System.out.println("**********************************************************************************");
            // Login Prompt
            System.out.print("Please select your login type: \n (1) Staff \n (2) Member \n (X) Exit \n>>");
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
                memberLogin(scanner, library, items, newItems, members, newMembers, loans, newLoans);
            } else if (loginType.equalsIgnoreCase("X")) {
                // Exit program
                System.out.println("Exiting the Library System. Goodbye!");

                return;
            } else {
                System.out.println("Invalid selection. Retry...");
            }
        }
    }

    private static void staffMenu(Scanner scanner, Library library, List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers) {
        while (true) {
            System.out.println("**********************************************************************************");
            System.out.println("Available commands: \n(1) add item, \n(2) add member, \n(3) list items, \n(4) list members, \n(5) list loans, \n(X) exit");
            System.out.println("**********************************************************************************");
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("..")) {
                break; // Return to main menu
            }

            switch (command) {
                case "1":
                    Staff.addItem(scanner, library, items, newItems);
                    break;

                case "2":
                    Staff.addMember(scanner, library, members, newMembers);
                    break;

                case "3":
                    Library.listItems(library);
                    break;

                case "4":
                    Library.listMembers(members);
                    break;

                case "5":
                    Library.listLoans(library);
                    break;

                case "x":
                    exitProgram(scanner, library, newItems, newMembers, null);
                    return;

                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }
        }
    }

    private static void memberLogin(Scanner scanner, Library library,  List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers, List<Loans> loans, List<Loans> newLoans) {
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
            memberMenu(scanner, library, currentMember, items, newItems, members, newMembers, loans, newLoans);
        } else {
            System.out.println("Too many invalid attempts. Returning to main menu...");
        }
    }

    private static void memberMenu(Scanner scanner, Library library, Member currentMember, List<Item> items, List<Item> newItems, List<Member> members, List<Member> newMembers, List<Loans> loans, List<Loans> newLoans) {
        while (true) {
            System.out.println("**********************************************************************************");
            System.out.println("Available commands: \n(1) list items, \n(2) checkout item, \n(3) return item, \n(4) renew item, \n(5) view fine, \n(6) pay fine, \n(7) view loans \n(X) exit");
            System.out.println("**********************************************************************************");
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("..")) {
                break; // Return to main menu
            }

            switch (command) {

                case "1":
                    Library.listItems(library);
                    break;

                case "2":
                    Member.checkoutItem(scanner, library, currentMember,   items,  newItems,  members,  newMembers,  loans,  newLoans);
                    break;

                case "3":
                    Member.returnItem(scanner, library, currentMember,   items,  newItems,  members,  newMembers,  loans,  newLoans);
                    //returnItem(scanner, currentMember);
                    break;

                case "4":
                    Member.renewItem(scanner, library, currentMember,   items,  newItems,  members,  newMembers,  loans,  newLoans);
                    //renewItem(scanner, currentMember);
                    break;

                case "5":
                    Member.viewFine(scanner, library, currentMember,   items,  newItems,  members,  newMembers,  loans,  newLoans);
                    break;

                case "6":
                    Member.payFine(scanner, library, currentMember,   items,  newItems,  members,  newMembers,  loans,  newLoans);
                    //payFine(scanner, currentMember);
                    break;

                case "7":
                    Member.viewLoans(scanner, library, currentMember,   items,  newItems,  members,  newMembers,  loans,  newLoans);
                    //payFine(scanner, currentMember);
                    break;

                case "x":
                    exitProgram(scanner, library, null, null, newLoans);
                    return;

                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }
        }
    }

    private static void exitProgram(Scanner scanner, Library library, List<Item> newItems, List<Member> newMembers, List<Loans> newLoans) {
        CSVUtil.saveOnExit(scanner, library, newItems, newMembers, newLoans);
        System.out.println("Exiting the Library System. Goodbye!");
    }
}

