package nivel1.Ext1;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("Must enter a directory and an output file\n");
            return;
        }
        File alpha = new File(args[0]);
        String[] content = AlphabeticalList.listAlphabetically(alpha);
        System.out.println("\n--- Alphabetical List ---\n");
        for (int i = 0; i < content.length; i++) {
            System.out.println(content[i]);
        }
        File outputFile = new File(args[1]);
        List<String> tree = AlphabeticalList.listDirectoryTree(alpha);
        AlphabeticalList.saveToFile(tree, outputFile);
        System.out.println("Tree saved to " + outputFile.getPath());
    }
}

