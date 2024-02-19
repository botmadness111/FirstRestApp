package ru.andrey.FirstRestApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.andrey.FirstRestApp.models.Measurement;

@Component
public class MeasurementsDAO {
    private final JdbcTemplate jdbcTemplate;

    public MeasurementsDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getRainyDaysCount(){
        return jdbcTemplate.query("select * from measurements where raining=true", new BeanPropertyRowMapper<>(Measurement.class)).size();
    }
}
