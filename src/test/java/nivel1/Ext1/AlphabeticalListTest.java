package nivel1.Ext1;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
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

}

