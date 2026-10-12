package nivel1.Ext1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AlphabeticalList {

    private static final DateTimeFormatter TIMESTAMP =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String[] listAlphabetically(File directory) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Not a valid directory: " + directory.getPath());
        }

        String[] content = directory.list();
        if (content == null) {
            return new String[0];
        }
        Arrays.sort(content, String.CASE_INSENSITIVE_ORDER);
        return content;
    }

    public static List<String> listDirectoryTree(File rootDirectory) {
        if (!rootDirectory.isDirectory()) {
            throw new IllegalArgumentException("Not a valid directory: " + rootDirectory.getPath());
        }
        List<String> result = new ArrayList<>();
        appendTree(rootDirectory, 0, result);
        return result;
    }

    private static void appendTree(File current, int depth, List<String> result) {
        File[] files = current.listFiles();
        if (files == null) {
            return;
        }

        Arrays.sort(files, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));

        for (File file : files) {
            String type = file.isDirectory() ? "(D)" : "(F)";
            Instant instant = Instant.ofEpochMilli(file.lastModified());
            String date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                    .format(TIMESTAMP);
            String indent = "    ".repeat(depth);
            result.add(indent + type + " " + file.getName() + " " + date);

            if (file.isDirectory()) {
                appendTree(file, depth + 1, result);
            }
        }
    }

    public static void saveToFile(List<String> toWrite, File outputFile) throws IOException {
        Files.write(outputFile.toPath(), toWrite);
    }

    public static List<String> readFile(File inputFile) throws IOException {
        if (!inputFile.isFile()) {
            throw new IllegalArgumentException("Not a valid file: " + inputFile.getPath());
        }
        return Files.readAllLines(inputFile.toPath());
    }
}