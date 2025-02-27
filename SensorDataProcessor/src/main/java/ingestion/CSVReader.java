package ingestion;

import data.SensorReading;
import data.Threshold;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVReader {
    private static final Logger logger = Logger.getLogger(CSVReader.class.getName());

    public static List<SensorReading> readSensorData(String filePath) {
        List<SensorReading> sensorReadings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 5) {
                    logger.log(Level.WARNING, "Skipping malformed row: " + line);
                    continue;
                }

                String dateString = parts[0].trim();
                String sensorType = parts[1].trim();
                String valueString = parts[2].trim();
                String unit = parts[3].trim();
                String locationIdString = parts[4].trim();

                if (dateString.isEmpty() || valueString.isEmpty() || locationIdString.isEmpty()) {
                    logger.log(Level.WARNING, "Skipping row with missing values: " + line);
                    continue;
                }

                try {
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    double value = Double.parseDouble(valueString);
                    int locationId = (int) Double.parseDouble(locationIdString);
                    sensorReadings.add(new SensorReading(date, sensorType, value, unit, locationId));
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Skipping invalid row: " + line, e);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading sensor data from file: " + filePath, e);
        }
        return sensorReadings;
    }


    public static List<Threshold> readThresholds(String filePath) {
        List<Threshold> thresholds = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    thresholds.add(new Threshold(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading thresholds: " + filePath, e);
        }
        return thresholds;
    }
}
