package service;

import com.sun.javafx.binding.StringFormatter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CSVService {
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public static List<String[]> readFromFile(String fileName) {
        List<String[]> data = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { // read the first line from the text file String line = br.readLine(); // loop until all lines are read while (line != null) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                data.add(attributes);

                line = br.readLine();
            }

        } catch (NoSuchFileException e) {
            System.out.println("File " + fileName + " does not exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void writeDataToFile(String fileName, String[] data) {
        Path pathToFile = Paths.get(fileName);
        try {
            FileWriter fw = new FileWriter(fileName, true);

            for (String attribute : data) {
                fw.append(attribute);
                fw.append(",");
            }
            fw.append("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createHeaderFile(String fileName, String[] header) {
        writeDataToFile(fileName, header);
    }

    public static boolean fileExists(String fileName) {
        return Files.exists(Paths.get(fileName));
    }
}
