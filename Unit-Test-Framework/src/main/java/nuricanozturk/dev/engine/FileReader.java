package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.CsvFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    private Class<?> $class;

    public void set$class(Class<?> $class) {
        this.$class = $class;
    }

    public String readFileCsvFormat(CsvFile csvFile) {
        var path = csvFile.value();
        var filePath = Path.of(path);
        var sb = new StringBuilder();

        try (var lines = Files.lines(filePath)) {
            lines.map(line -> line.split("\\s+"))
                    .map(words -> String.join(",", words))
                    .forEach(sb::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
