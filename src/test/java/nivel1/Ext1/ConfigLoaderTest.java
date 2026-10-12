package nivel1.Ext1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigLoaderTest {

    @Test
    void loadsConfigFromFile(@TempDir File folder) throws IOException {
        File configFile = new File(folder, "config.properties");
        Files.write(configFile.toPath(), List.of(
                "input.directory=src",
                "output.file=tree.txt"
        ));

        Config config = ConfigLoader.load(configFile.getPath());

        assertThat(config.getInputDirectory()).isEqualTo("src");
        assertThat(config.getOutputFile()).isEqualTo("tree.txt");
    }

    @Test
    void throwsWhenInputDirectoryIsMissing(@TempDir File folder) throws IOException {
        File configFile = new File(folder, "config.properties");
        Files.write(configFile.toPath(), List.of("output.file=tree.txt"));

        assertThatThrownBy(() -> ConfigLoader.load(configFile.getPath()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("input.directory");
    }

    @Test
    void throwsWhenFileDoesNotExist(@TempDir File folder) {
        File missing = new File(folder, "does-not-exist.properties");

        assertThatThrownBy(() -> ConfigLoader.load(missing.getPath()))
                .isInstanceOf(IOException.class);
    }
}

