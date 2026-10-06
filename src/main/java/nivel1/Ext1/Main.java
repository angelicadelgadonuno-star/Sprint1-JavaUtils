package nivel1.Ext1;

import java.io.File;

public class Main {
    static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("You must specify a directory as a parameter.\n");
            return;
        }
        File alpha = new File(args[0]);
        String[] content = AlphabeticalList.listAlphabetically(alpha);
        for (int i = 0; i < content.length; i++) {
            System.out.println(content[i]);
        }
    }
}

