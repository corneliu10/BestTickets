package utilities;

import logging.Logger;
import service.CSVService;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class CSVUtilities<T> {
    public void writeToFile(T obj, String fileName) {
        String[] attributes = extractAttributes(obj);
        CSVService.writeDataToFile(fileName, attributes);
    }

    public abstract String[] extractAttributes(T obj);

    public void loadObjects(String fileName, Consumer<String[]> loadObj) {
        List<String[]> attributes = CSVService.readFromFile(fileName);
        attributes.remove(0); // removing header

        Logger.getInstance().info("Loading " + fileName + "...");
        for (String[] attribute : attributes) {
            try {
                loadObj.accept(attribute);
            } catch (Exception e) {
                Logger.getInstance().error("Error on loading " + Arrays.toString(attribute));
                e.printStackTrace();
            }
        }

        Logger.getInstance().info("Objects loaded!");
    }

    public void showObjects(List<T> objs) {
        for (T obj : objs) {
            System.out.println(obj);
        }
    }
}