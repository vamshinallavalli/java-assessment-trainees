package processing;

import data.SensorReading;
import java.util.*;
import java.util.stream.Collectors;

public class DataProcessor {
    public static Map<String, Map<Integer, Double>> calculateMonthlyAverages(List<SensorReading> readings) {
        return readings.stream()
                .collect(Collectors.groupingBy(SensorReading->SensorReading.getSensorType(),
                        Collectors.groupingBy(r -> r.getDate().getMonthValue(),
                                Collectors.averagingDouble(SensorReading->SensorReading.getValue()))));
    }
}