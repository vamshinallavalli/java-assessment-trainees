package reporting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class CSVWriter {
    public static void writeCSV(String filePath, List<String[]> data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (String[] row : data) {
            writer.write(String.join(",", row));
            writer.newLine();
        }
        writer.close();
    }
}
