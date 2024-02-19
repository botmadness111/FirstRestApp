package ru.andrey.FirstRestApp.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.andrey.FirstRestApp.dto.SensorDTO;
import ru.andrey.FirstRestApp.services.SensorService;

@Component
public class SensorValidator implements Validator {
    private final SensorService personService;

    @Autowired
    public SensorValidator(SensorService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;

        if (personService.searchByName(sensor.getName()) != null) {
            errors.rejectValue("name", "", "name should be unique");
        }
    }
}
