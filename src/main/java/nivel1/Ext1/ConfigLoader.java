package nivel1.Ext1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final String KEY_INPUT_DIRECTORY = "input.directory";
    private static final String KEY_OUTPUT_FILE = "output.file";

    public static Config load(String path) throws IOException {
        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream(path)) {
            properties.load(in);
        }

        String inputDirectory = properties.getProperty(KEY_INPUT_DIRECTORY);
        String outputFile = properties.getProperty(KEY_OUTPUT_FILE);

        if (inputDirectory == null || inputDirectory.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing property: " + KEY_INPUT_DIRECTORY);
        }
        if (outputFile == null || outputFile.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing property: " + KEY_OUTPUT_FILE);
        }
        return new Config(inputDirectory, outputFile);
    }
}

