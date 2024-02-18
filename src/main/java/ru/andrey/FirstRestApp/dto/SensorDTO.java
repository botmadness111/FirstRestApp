package ru.andrey.FirstRestApp.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.andrey.FirstRestApp.models.Measurement;

public class SensorDTO {

    @Size(min = 3, max=100, message = "name should be between 3 and 100")
    private String name;

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
