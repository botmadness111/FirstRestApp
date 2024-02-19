package ru.andrey.FirstRestApp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@SpringBootApplication
public class FirstRestAppApplication {
    private final Environment environment;

    @Autowired
    public FirstRestAppApplication(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(FirstRestAppApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(Objects.requireNonNull(environment.getRequiredProperty("hibernate.driver_class")));
        dataSource.setUrl(environment.getProperty("hibernate.connection.url"));
        dataSource.setUsername(environment.getProperty("hibernate.connection.username"));
        dataSource.setPassword(environment.getProperty("hibernate.connection.password"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
