package com.sustainable.commute.models;

// Blueprint used in StationController
public class Station {
    private String value;
    private String label;

    public Station(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
