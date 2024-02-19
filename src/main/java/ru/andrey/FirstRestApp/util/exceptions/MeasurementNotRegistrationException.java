package ru.andrey.FirstRestApp.util.exceptions;

public class MeasurementNotRegistrationException extends RuntimeException {
    String message;

    public MeasurementNotRegistrationException(String message) {
        super(message);
        this.message = message;
    }

}
