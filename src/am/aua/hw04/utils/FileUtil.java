package am.aua.hw04.utils;

import am.aua.hw04.core.MalformedStringParameterException;
import am.aua.hw04.core.Workweek;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static void save(Workweek workweek, String path) throws IOException {
        String[] workweekStrings = workweek.toStringArray();
        saveStringsToFile(workweekStrings, path);
    }

    public static Workweek load(String path) throws IOException {
        String[] lines = loadStringsFromFile(path);
        try {
            return Workweek.fromStringArray(lines);
        } catch (MalformedStringParameterException e) {
            throw new IOException("Error parsing workweek from file", e);
        }
    }


    public static void saveStringsToFile(String[] content, String path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static String[] loadStringsFromFile(String path) throws IOException {
        List<String> linesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                linesList.add(line);
            }
        }
        return linesList.toArray(new String[0]);
    }
}
