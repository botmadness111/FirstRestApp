package ru.andrey.FirstRestApp.util.exceptions;


public class SensorNotRegistrationException extends RuntimeException {

    public SensorNotRegistrationException(String message) {
        super(message);
    }
}
