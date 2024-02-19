package ru.andrey.FirstRestApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import ru.andrey.FirstRestApp.models.Sensor;

public class MeasurementDTO {
    @Min(value = -100, message = "value should be between -100 and 100")
    @Max(value = 100, message = "value should be between -100 and 100")
    private float value;

    //@NotNull(message = "raining should be :)")
    private boolean raining;

    //@NotNull(message = "should be sensor")
    @JsonIgnore
    private Sensor sensor;


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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
