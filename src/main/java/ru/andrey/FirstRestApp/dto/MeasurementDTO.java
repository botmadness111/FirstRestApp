package ru.andrey.FirstRestApp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class MeasurementDTO {
    private float value;
    private boolean raining;


    public MeasurementDTO() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                '}';
    }
}
