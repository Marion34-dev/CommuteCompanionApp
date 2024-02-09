package com.sustainable.commute;

import java.time.Instant;

// Defining the data (and format) to extract from the API call
public class ArrivalInfo {
    private String lineName;
    private String platformName;
    private int timeToStationMinutes;
    private Instant expectedArrivalTime;
    private String destinationName;
    private String modeName;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public int getTimeToStationMinutes() {
        return timeToStationMinutes;
    }

    public void setTimeToStationMinutes(int timeToStationMinutes) {
        this.timeToStationMinutes = timeToStationMinutes;
    }

    public Instant getExpectedArrivalTime() {
        return expectedArrivalTime;
    }

    public void setExpectedArrivalTime(Instant expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
    }

    public String getTimeToStationDisplay() {
        if (timeToStationMinutes == 0) {
            return "arrived";
        } else {
            return String.valueOf(timeToStationMinutes);
        }
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }
}
