package ru.andrey.FirstRestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.andrey.FirstRestApp.dto.MeasurementDTO;
import ru.andrey.FirstRestApp.models.Measurement;
import ru.andrey.FirstRestApp.services.MeasurementService;
import ru.andrey.FirstRestApp.util.exceptions.MeasurementErrorException;
import ru.andrey.FirstRestApp.util.exceptions.MeasurementNotRegistrationException;
import ru.andrey.FirstRestApp.util.validators.MeasurementValidator;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping()
    public ResponseEntity<List<MeasurementDTO>> getMeasurements() {
        return new ResponseEntity<>(measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            }

            throw new MeasurementNotRegistrationException(errorMessage.toString());

        }

        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount(){
        return measurementService.getRainyDaysCount();
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorException> handError(MeasurementNotRegistrationException e) {
        MeasurementErrorException response = new MeasurementErrorException(e.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
