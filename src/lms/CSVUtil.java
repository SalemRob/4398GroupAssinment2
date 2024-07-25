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

    public static void writeCSV(String filePath, List<List<String>> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            for (List<String> record : data) {
                writer.writeNext(record.toArray(new String[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
