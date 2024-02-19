package ru.andrey.FirstRestApp.util.exceptions;

import java.util.Date;

public class MeasurementErrorException {
    String message;
    Date date;

    public MeasurementErrorException(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
