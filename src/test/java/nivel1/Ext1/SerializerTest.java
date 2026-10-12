package nivel1.Ext1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SerializerTest {

    @Test
    void serializeCreatesFile(@TempDir File folder) throws IOException {
        File outputFile = new File(folder, "person.ser");
        Person original = new Person("Ana", 30);
        Serializer.serializeObject(original, outputFile);
        assertThat(outputFile).exists();
    }

    @Test
    void serializesAndDeserializesPerson(@TempDir File folder) throws IOException, ClassNotFoundException {
        File file = new File(folder, "person.ser");
        Person original = new Person("Ana", 30);
        Serializer.serializeObject(original, file);
        Object result = Serializer.deserializeObject(file);
        assertThat(result).isInstanceOf(Person.class);
        assertThat(result).usingRecursiveComparison().isEqualTo(original);
    }

    @Test
    void deserializeThrowsExceptionWhenFileDoesNotExist(@TempDir File folder) {
        File missing = new File(folder, "nonExistent.ser");
        assertThatThrownBy(() -> Serializer.deserializeObject(missing)).isInstanceOf(FileNotFoundException.class);
    }
}