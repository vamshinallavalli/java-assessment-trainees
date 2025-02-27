package processing;

import data.SensorReading;
import data.Threshold;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class OutlierDetector {

    public static List<SensorReading> detectOutliers(List<SensorReading> readings, Map<String, Threshold> thresholds) {
        List<SensorReading> outliers = new ArrayList<>();
        for (SensorReading reading : readings) {
            Threshold threshold = thresholds.get(reading.getSensorType());
            if (threshold != null && (reading.getValue() < threshold.getMinThreshold() || reading.getValue() > threshold.getMaxThreshold())) {
                outliers.add(reading);
            }
        }
        return outliers;
    }
}
