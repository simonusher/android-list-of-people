package com.a235040.listofpeople;

/**
 * Created by Szymon on 20.03.2018.
 */

public class DateIncorrectException extends IllegalArgumentException {
    public enum DateErrorType {
        FUTURE_DATE_ERROR,
        DATE_FORMAT_ERROR,
        DATE_INCORRECT_ERROR,
    }
    private DateErrorType errorType;

    public DateIncorrectException(DateErrorType errorType) {
        this.errorType = errorType;
    }

    public DateErrorType getErrorType() {
        return errorType;
    }
}
