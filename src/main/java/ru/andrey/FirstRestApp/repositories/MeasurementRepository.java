package ru.andrey.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andrey.FirstRestApp.models.Measurement;

import java.util.Optional;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    Optional<Measurement> searchBySensorName(String name);
}
