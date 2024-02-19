package ru.andrey.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.andrey.FirstRestApp.dto.MeasurementDTO;
import ru.andrey.FirstRestApp.models.Measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class RestTemplateController {
    private final ModelMapper modelMapper;

    @Autowired
    public RestTemplateController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/post")
    public ResponseEntity<HttpStatus> send1000query() {
        RestTemplate restTemplate = new RestTemplate();

        Random random = new Random();
        Map<String, Object> json = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            json.put("value", random.nextInt(100) - random.nextInt(100));
            json.put("raining", random.nextBoolean());
            Map<String, String> map2 = new HashMap<>();
            map2.put("name", "test-sensor");
            json.put("sensor", map2);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(json);

            String url = "http://localhost:8080/measurements/add";

            String response = restTemplate.postForObject(url, request, String.class);

            System.out.println(response);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<MeasurementDTO>> getMeasurements() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/measurements";

        List<MeasurementDTO> response = restTemplate.getForObject(url, List.class);

        return new ResponseEntity<>(response, HttpStatus.OK);

        //return new ResponseEntity<>(response.stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
