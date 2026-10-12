package nivel1.Ext1;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    private static final String DEFAULT_CONFIG = "config.properties";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String configPath = (args.length > 0) ? args[0] : DEFAULT_CONFIG;
        Config config = ConfigLoader.load(configPath);

        File inputDirectory = new File(config.getInputDirectory());
        File outputFile = new File(config.getOutputFile());

        List<String> tree = AlphabeticalList.listDirectoryTree(inputDirectory);
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

        System.out.println("\n--- Recovered person ---");
        System.out.println(recoveredPerson.getName());
        System.out.println(recoveredPerson.getAge());
    }
}

