package nivel1.Ext1;

import java.io.File;
import java.util.Arrays;

public class AlphabeticalList {

    public static String[] listAlphabetically(File directory) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The path is not a valid directory " + directory.getPath());
        }
        String[] content = directory.list();
        Arrays.sort(content, String.CASE_INSENSITIVE_ORDER);
        return content;
    }
}


