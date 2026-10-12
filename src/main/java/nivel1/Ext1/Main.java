package nivel1.Ext1;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    static void main(String[] args) throws IOException, ClassNotFoundException {

        if (args.length < 2) {
            System.out.println("Must enter a directory and an output file\n");
            return;
        }
        File alpha = new File(args[0]);
        String[] content = AlphabeticalList.listAlphabetically(alpha);
        System.out.println("\n--- Alphabetical list ---\n");
        for (int i = 0; i < content.length; i++) {
            System.out.println(content[i]);
        }
        File outputFile = new File(args[1]);
        List<String> tree = AlphabeticalList.listDirectoryTree(alpha);
        AlphabeticalList.saveToFile(tree, outputFile);
        System.out.println("Tree saved to " + outputFile.getPath());

        List<String> fileContent = AlphabeticalList.readFile(outputFile);
        System.out.println("\n--- File content ---\n");
        for (String line : fileContent) {
            System.out.println(line);
        }

        Person one = new Person("Annie", 42);
        File personFile = new File("person.ser");
        Serializer.serializeObject(one, personFile);

        Person recoveredPerson = (Person) Serializer.deserializeObject(personFile);

        System.out.println(recoveredPerson.getName());
        System.out.println(recoveredPerson.getAge());
    }
}

