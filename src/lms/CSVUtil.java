package lms;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVUtil {
    public static List<List<String>> readCSV(String filePath) {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void writeCSV(String filePath, List<List<String>> data, boolean append) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, append))) {
            for (List<String> record : data) {
                writer.writeNext(record.toArray(new String[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveOnExit(Scanner scanner, Library library, List<Item> newItems, List<Member> newMembers, List<Loans> newLoans) {

        if (newItems != null && newMembers != null && newLoans != null) {
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
                    row.add(book.getAuthor());
                    row.add(book.getIsbn());
                    row.add(String.valueOf(book.isBestSeller()));
                    row.add("N/A");
                } else if (item instanceof AudioVisual) {
                    AudioVisual av = (AudioVisual) item;
                    row.add("AudioVisual");
                    row.add(av.getItemID());
                    row.add(av.getName());
                    row.add(av.getStatus());
                    row.add(String.valueOf(av.getPrice()));
                    row.add("N/A");
                    row.add("N/A");
                    row.add("N/A");
                    row.add(av.getFormat());
                }
                newItemData.add(row);
            }
            CSVUtil.writeCSV("src/lms/items.csv", newItemData, true);

            // Write only new members to the CSV file
            List<List<String>> newMemberData = new ArrayList<>();
            for (Member member : newMembers) {
                List<String> row = new ArrayList<>();
                row.add(member.getName());
                row.add(member.getAddress());
                row.add(member.getPhoneNumber());
                row.add(String.valueOf(member.getAge()));
                row.add(member.getLibraryCardNumber());
                row.add(String.valueOf(member.getNumLoans()));
                newMemberData.add(row);
            }
            CSVUtil.writeCSV("src/lms/members.csv", newMemberData, true);

            // Write only new loans to the CSV file
            List<List<String>> newLoanData = new ArrayList<>();
            for (Loans loan : newLoans) {
                List<String> row = new ArrayList<>();
                row.add(loan.getItemID());
                row.add(loan.getUserID());
                row.add(loan.getIssueDate().toString());  // Convert LocalDate to String
                row.add(loan.getDueDate().toString());    // Convert LocalDate to String
                newLoanData.add(row);
            }
            CSVUtil.writeCSV("src/lms/loans.csv", newLoanData, true);
        }


    }
}
