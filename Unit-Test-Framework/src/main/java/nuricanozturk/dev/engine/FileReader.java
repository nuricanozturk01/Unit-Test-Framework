/*----------------------------------------------------------------
	FILE		: FileReader.java
	AUTHOR		: Nuri Can OZTURK
	LAST UPDATE	: 02.05.2023
	FileReader class represent reading file with csv format.
	Copyleft (c) Unit-Test-Framework
	All Rights Free
----------------------------------------------------------------*/
package nuricanozturk.dev.engine;

import nuricanozturk.dev.annotation.CsvFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class FileReader
{
    public String readFileCsvFormat(CsvFile csvFile)
    {
        var path = csvFile.value();
        var filePath = Path.of(path);
        var sb = new StringBuilder();

        try (var lines = Files.lines(filePath))
        {
            lines.map(line -> line.split("\\s+"))
                    .map(words -> String.join(",", words))
                    .forEach(sb::append);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return sb.toString().replaceAll("\\s", "");
    }
}
