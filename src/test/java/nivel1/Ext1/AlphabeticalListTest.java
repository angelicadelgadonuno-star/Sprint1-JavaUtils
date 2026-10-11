package nivel1.Ext1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AlphabeticalListTest {

    @Test
    void returnsNamesInAlphabeticalOrder(@TempDir File folder) throws IOException {
        new File(folder, "b.txt").createNewFile();
        new File(folder, "c.txt").createNewFile();
        new File(folder, "a.txt").createNewFile();
        String[] result = AlphabeticalList.listAlphabetically(folder);
        assertThat(result).containsExactly("a.txt", "b.txt", "c.txt");
    }

    @Test
    void ignoresCaseWhenSorting(@TempDir File folder) throws IOException {
        new File(folder, "B.txt").createNewFile();
        new File(folder, "a.txt").createNewFile();
        String[] result = AlphabeticalList.listAlphabetically(folder);
        assertThat(result).containsExactly("a.txt", "B.txt");
    }

    @Test
    void returnsEmptyArrayWhenDirectoryIsEmpty(@TempDir File folder) {
        String[] result = AlphabeticalList.listAlphabetically(folder);
        assertThat(result).isEmpty();
    }

    @Test
    void throwsExceptionWhenPathIsAFile(@TempDir File folder) throws IOException {
        File file = new File(folder, "document.txt");
        file.createNewFile();
        assertThatThrownBy(() -> AlphabeticalList.listAlphabetically(file)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void throwsExceptionWhenPathDoesNotExist(@TempDir File folder) {
        File missing = new File(folder, "nonExisting");
        assertThatThrownBy(() -> AlphabeticalList.listAlphabetically(missing)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void listsSingleFileWithTypeAndName(@TempDir File folder) throws IOException {
        new File(folder, "a.txt").createNewFile();
        List<String> result = AlphabeticalList.listDirectoryTree(folder);
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).startsWith("F a.txt");
    }

    @Test
    void listsContentOfSubdirectoriesRecursively(@TempDir File folder) throws IOException {
         File sub = new File(folder, "sub");
        sub.mkdir();
        new File(sub, "b.txt").createNewFile();
        List<String> result = AlphabeticalList.listDirectoryTree(folder);
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).startsWith("D sub");
        assertThat(result.get(1)).startsWith("F b.txt");
    }

    @Test
    void listsEachLevelInAlphabeticalOrder(@TempDir File folder) throws IOException {
        new File(folder, "c.txt").createNewFile();
        new File(folder, "a.txt").createNewFile();
        new File(folder, "b.txt").createNewFile();
        List<String> result = AlphabeticalList.listDirectoryTree(folder);
        assertThat(result).hasSize(3);
        assertThat(result.get(0)).startsWith("F a.txt");
        assertThat(result.get(1)).startsWith("F b.txt");
        assertThat(result.get(2)).startsWith("F c.txt");
    }

    @Test
    void listDirectoryTreeThrowsExceptionWhenPathIsAFile(@TempDir File folder) throws IOException {
        File file = new File(folder, "a.txt");
        file.createNewFile();
        assertThatThrownBy(() -> AlphabeticalList.listDirectoryTree(file)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void listDirectoryTreeThrowsExceptionWhenPathDoesNotExist(@TempDir File folder) {
        File missing = new File(folder, "d.txt");
        assertThatThrownBy(() -> AlphabeticalList.listDirectoryTree(missing)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void saveLinesToFile(@TempDir File folder) throws IOException {
        File outputFile = new File(folder, "tree.txt");
        List<String> newLines = List.of("one", "two", "three", "four", "five");
        AlphabeticalList.saveToFile(newLines, outputFile);
        List<String> savedLines = Files.readAllLines(outputFile.toPath());
        assertThat(savedLines).containsExactlyElementsOf(newLines);
    }

    @Test
    void readsAllLinesOfFile(@TempDir File folder) throws IOException {
        File inputFile = new File(folder, "notes.txt");
        List<String> lines = List.of("one", "two", "three", "four", "five");
        Files.write(inputFile.toPath(), lines);
        List<String> result = AlphabeticalList.readFile(inputFile);
        assertThat(result).containsExactlyElementsOf(lines);
    }

    @Test
    void readFileThrowsExceptionWhenPathIsADirectory(@TempDir File folder) {
        assertThatThrownBy(() -> AlphabeticalList.readFile(folder)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void readFileThrowsExceptionWhenPathDoesNotExist(@TempDir File folder) {
        File missing = new File(folder, "nonExistent.txt");
        assertThatThrownBy(() -> AlphabeticalList.readFile(missing)).isInstanceOf(IllegalArgumentException.class);
    }
}

