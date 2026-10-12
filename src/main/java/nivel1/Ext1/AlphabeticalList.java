package nivel1.Ext1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.time.Instant;
import java.time.ZoneId;

public class AlphabeticalList {

    public static String[] listAlphabetically(File directory) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The path is not a valid directory " + directory.getPath());
        }

        String[] content = directory.list();
        Arrays.sort(content, String.CASE_INSENSITIVE_ORDER);
        return content;
    }

    public static List<String> listDirectoryTree(File rootDirectory) {
        if (!rootDirectory.isDirectory()) {
            throw new IllegalArgumentException("The path is not a valid directory " + rootDirectory.getPath());
        }

        List<String> result = new ArrayList<>();
        File[] files = rootDirectory.listFiles();

        if (files == null) {
            return result;
        }

        Arrays.sort(files, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));
        DateTimeFormatter timeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (File file : files) {
            String type;
            if (file.isDirectory()) {
                type = "D";
            } else {
                type = "F";
            }
            Instant instant = Instant.ofEpochMilli(file.lastModified());
            String date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(timeStamp);
            result.add(type + " " + file.getName() + " " + date);
            if (file.isDirectory()) {
                result.addAll(listDirectoryTree(file));
            }
        }
        return result;
    }

    public static void saveToFile(List<String> toWrite, File outputFile) throws IOException {
        Files.write(outputFile.toPath(), toWrite);
    }

    public static List<String> readFile(File inputFile) throws IOException {
        if (!inputFile.isFile()) {
            throw new IllegalArgumentException("The path is not a valid file " + inputFile.getPath());
        }
        return Files.readAllLines(inputFile.toPath());
    }
}


