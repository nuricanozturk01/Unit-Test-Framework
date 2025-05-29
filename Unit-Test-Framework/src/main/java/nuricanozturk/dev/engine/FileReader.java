/*----------------------------------------------------------------
	FILE		: FileReader.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	FileReader class represent reading file with csv format.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import nuricanozturk.dev.annotation.CsvFile;

final class FileReader {
  public String readFileCsvFormat(final CsvFile csvFile) {
    final var path = csvFile.value();
    final var filePath = Path.of(path);
    final var sb = new StringBuilder();

    try (final var lines = Files.lines(filePath)) {
      lines
          .map(line -> line.split("\\s+"))
          .map(words -> String.join(",", words))
          .forEach(sb::append);
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return sb.toString().replaceAll("\\s", "");
  }
}
