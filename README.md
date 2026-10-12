# Sprint 1 — Java Utils

Java utilities for file handling and object serialization.
All programs run from the command line.

## Requirements

- JDK 17 or higher
- macOS, Linux or Windows

## Project structure

```
Sprint1-JavaUtils/
├── config.properties
├── pom.xml
├── README.md
└── src/
    ├── main/java/nivel1/Ext1/
    │   ├── Main.java
    │   ├── AlphabeticalList.java
    │   ├── Person.java
    │   ├── Serializer.java
    │   ├── Config.java
    │   └── ConfigLoader.java
    └── test/java/nivel1/Ext1/
        ├── AlphabeticalListTest.java
        ├── SerializerTest.java
        └── ConfigLoaderTest.java
```

## How to compile

Open a terminal in the project root and run:

```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
```

On Windows (cmd):

```bat
mkdir out
dir /s /b src\main\java\*.java > sources.txt
javac -d out @sources.txt
```

The compiled files go to the `out` folder.

## Level 1 — File utilities

### How to run

```bash
java -cp out nivel1.Ext1.Main
```

The program reads its settings from `config.properties` (see Level 2).

It performs five actions, in this order:

1. Lists the contents of the directory in alphabetical order (Exercise 1).
2. Builds a tree of the directory with all its levels, using `(D)` for
   directories and `(F)` for files, and shows the last modified date
   (Exercise 2).
3. Saves the tree to a text file (Exercise 3).
4. Reads the text file and prints its content on the screen (Exercise 4).
5. Serializes a `Person` object to a `.ser` file, then deserializes it
   (Exercise 5).

The file `person.ser` is created in the current folder. This keeps the
program portable on any operating system.

### Portability

- The code does not use absolute paths.
- File paths are built with `File` and `Path`, so they work on any
  operating system.
- Text files are written and read in UTF-8.

## Level 2 — Configuration file

In level 2, the input directory and the output file are read from a
configuration file instead of the command line. This separates the
configuration from the program logic.

### Configuration file

The file `config.properties` (in the project root) contains:

```properties
input.directory=src
output.file=tree.txt
```

- `input.directory` → directory to read.
- `output.file` → name and path of the resulting TXT file.

### How to run

The program runs the same way as in level 1:

```bash
java -cp out nivel1.Ext1.Main
```

It reads `config.properties` from the current folder.

You can also pass a different configuration file:

```bash
java -cp out nivel1.Ext1.Main custom.properties
```

### Change the behavior without recompiling

Edit `config.properties` and run the program again:

```properties
input.directory=src/test
output.file=test-tree.txt
```

The program will read `src/test` and write `test-tree.txt`, without
recompiling.

## Tests

The tests use JUnit 5 and AssertJ.

Run them from IntelliJ:

- Right-click on `src/test/java` → **Run 'All Tests'**.

Or with Maven, if you have it installed:

```bash
mvn test
```

The project has 20 tests covering:

- alphabetical listing of a directory,
- recursive tree with `(D)`/`(F)` markers and dates,
- reading and writing text files,
- object serialization and deserialization,
- reading the configuration file.