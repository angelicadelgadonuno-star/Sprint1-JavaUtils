package nivel1.Ext1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
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
        new File(folder,"B.txt").createNewFile();
        new File(folder,"a.txt").createNewFile();
        String[] result = AlphabeticalList.listAlphabetically(folder);
        assertThat(result).containsExactly("a.txt","B.txt");
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
    void throwsExceptionWhenPathDoesNotExist (@TempDir File folder) {
        File missing = new File(folder, "nonExisting");
        assertThatThrownBy(() -> AlphabeticalList.listAlphabetically(missing)).isInstanceOf(IllegalArgumentException.class);
    }
}


