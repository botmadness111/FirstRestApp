package ru.andrey.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrey.FirstRestApp.models.Sensor;
import ru.andrey.FirstRestApp.repositories.SensorRepository;
import ru.andrey.FirstRestApp.util.validators.SensorValidator;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Sensor findById(int id) {
        return sensorRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        enrichSensor(sensor);
        sensorRepository.save(sensor);
    }

    public Sensor searchByName(String name) {
        return sensorRepository.searchByName(name);
    }

    private void enrichSensor(Sensor sensor) {
        sensor.setDate_of_registration(new Date());
    }
}
