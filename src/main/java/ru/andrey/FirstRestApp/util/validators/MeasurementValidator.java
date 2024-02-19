package ru.andrey.FirstRestApp.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.andrey.FirstRestApp.dto.MeasurementDTO;
import ru.andrey.FirstRestApp.models.Measurement;
import ru.andrey.FirstRestApp.services.MeasurementService;
import ru.andrey.FirstRestApp.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if (sensorService.searchByName(measurementDTO.getSensor().getName()) == null){
            errors.rejectValue("sensor", "", "A sensor with this name was not found");
        }
    }
}
