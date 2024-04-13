package ru.andrey.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrey.FirstRestApp.dao.MeasurementsDAO;
import ru.andrey.FirstRestApp.models.Measurement;
import ru.andrey.FirstRestApp.models.Sensor;
import ru.andrey.FirstRestApp.repositories.MeasurementRepository;
import ru.andrey.FirstRestApp.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final MeasurementsDAO measurementsDAO;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository, MeasurementsDAO measurementsDAO) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
        this.measurementsDAO = measurementsDAO;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        Sensor sensor = sensorRepository.searchByName(measurement.getSensors().get(0).getName());
//        measurement.setSensor(sensor);
        measurement.addSensor(sensor);
        sensor.addMeasurement(measurement);
        measurementRepository.save(measurement);
    }

//    public Optional<Measurement> searchBySensorName(String name){
//        return measurementRepository.searchBySensorName(name);
//    }

    public Integer getRainyDaysCount(){
        return measurementsDAO.getRainyDaysCount();
    }


}
