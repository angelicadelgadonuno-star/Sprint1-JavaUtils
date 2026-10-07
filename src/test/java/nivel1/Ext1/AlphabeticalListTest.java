package nivel1.Ext1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(result).containsExactly("a.txt","B.txt  ");
    }



}


