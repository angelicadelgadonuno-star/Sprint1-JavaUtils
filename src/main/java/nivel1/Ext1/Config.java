package nivel1.Ext1;

public class Config {

    private final String inputDirectory;
    private final String outputFile;

    public Config(String inputDirectory, String outputFile) {
        this.inputDirectory = inputDirectory;
        this.outputFile = outputFile;
    }

    public String getInputDirectory() {
        return inputDirectory;
    }

    public String getOutputFile() {
        return outputFile;
    }
}
