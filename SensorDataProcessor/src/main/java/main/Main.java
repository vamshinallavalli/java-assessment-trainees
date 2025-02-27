package main;

import ingestion.CSVReader;
import data.SensorReading;
import data.Threshold;
import processing.DataProcessor;
import processing.OutlierDetector;
import reporting.CSVWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        try {
            List<SensorReading> readings = CSVReader.readSensorData("C:\\Users\\VamshiNallavalli\\OneDrive - Atyeti Inc\\Desktop\\Test\\java-assessment-trainees\\SensorDataProcessor\\src\\data\\sensor_data.csv");
            List<Threshold> thresholdList = CSVReader.readThresholds("C:\\Users\\VamshiNallavalli\\OneDrive - Atyeti Inc\\Desktop\\Test\\java-assessment-trainees\\SensorDataProcessor\\src\\data\\thresholds.csv");
            Map<String, Threshold> thresholds = new HashMap<>();
            for (Threshold t : thresholdList) {
                thresholds.put(t.getSensorType(), t);
            }
            Map<String, Map<Integer, Double>> monthlyAverages = DataProcessor.calculateMonthlyAverages(readings);
            List<SensorReading> outliers = OutlierDetector.detectOutliers(readings, thresholds);
            CSVWriter.writeCSV("C:\\Users\\VamshiNallavalli\\OneDrive - Atyeti Inc\\Desktop\\Test\\java-assessment-trainees\\SensorDataProcessor\\src\\output\\monthly_stats.csv", Collections.singletonList(new String[]{"sensor_type", "month", "avg_value"}));
            CSVWriter.writeCSV("C:\\Users\\VamshiNallavalli\\OneDrive - Atyeti Inc\\Desktop\\Test\\java-assessment-trainees\\SensorDataProcessor\\src\\output\\outliers.csv", Collections.singletonList(new String[]{"date", "sensor_type", "value", "unit", "location_id"}));
        } catch (IOException e) {
            System.err.println("Error reading CSV files: " + e.getMessage());
        }
    }
}