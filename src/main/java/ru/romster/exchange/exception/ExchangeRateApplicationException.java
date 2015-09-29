package ru.romster.exchange.exception;

/**
 * Created by romster on 29.09.15.
 */
public class ExchangeRateApplicationException extends Exception {
    public ExchangeRateApplicationException() {
    }

    public ExchangeRateApplicationException(String message) {
        super(message);
    }

    public ExchangeRateApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExchangeRateApplicationException(Throwable cause) {
        super(cause);
    }

    public ExchangeRateApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrorTextMessage() {
        return "Application error";
    }
}

