package ru.andrey.FirstRestApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.Collection;
import ru.andrey.FirstRestApp.services.MeasurementService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_registration")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date_of_registration;

    @JsonBackReference
    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.Date getDate_of_registration() {
        return date_of_registration;
    }

    public void setDate_of_registration(Date date_of_registration) {
        this.date_of_registration = date_of_registration;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public void addMeasurement(Measurement measurement){
        if (measurements == null){
            measurements = new ArrayList<>(List.of(measurement));
        }else
            measurements.add(measurement);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_of_registration=" + date_of_registration +
                '}';
    }
}
