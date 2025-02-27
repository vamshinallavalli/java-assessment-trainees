package data;

import java.time.LocalDate;

public class SensorReading {
    private LocalDate date;
    private String sensorType;
    private double value;
    private String unit;
    private int locationId;

    public SensorReading(LocalDate date, String sensorType, double value, String unit, int locationId) {
        this.date = date;
        this.sensorType = sensorType;
        this.value = value;
        this.unit = unit;
        this.locationId = locationId;
    }

    public LocalDate getDate() { return date; }
    public String getSensorType() { return sensorType; }
    public double getValue() { return value; }
    public String getUnit() { return unit; }
    public int getLocationId() { return locationId; }
}
