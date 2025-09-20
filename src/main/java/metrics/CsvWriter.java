package metrics;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    public static void write(String filename, String header, String row) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            if (writer.getEncoding() != null && writer.toString().isEmpty()) {
                writer.write(header + "\n");
            }
            writer.write(row + "\n");
        }
    }
}