package nivel1.Ext1;

import java.io.File;
import java.util.List;

public class Main {

    static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("You must specify a directory as a parameter.\n");
            return;
        }
        File alpha = new File(args[0]);
        String[] content = AlphabeticalList.listAlphabetically(alpha);
        System.out.println("\n--- Alphabetical List ---\n");
        for (int i = 0; i < content.length; i++) {
            System.out.println(content[i]);
        }

        List<String> tree = AlphabeticalList.listDirectoryTree(alpha);
        System.out.println("\n--- Directory tree ---\n");
        for (String line : tree) {
            System.out.println(line);
        }
    }
}

