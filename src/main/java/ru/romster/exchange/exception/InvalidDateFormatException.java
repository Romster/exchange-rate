package ru.romster.exchange.exception;

import ru.romster.exchange.model.Rate;

/**
 * Created by romster on 29.09.15.
 */
public class InvalidDateFormatException extends ExchangeRateApplicationException {

    public InvalidDateFormatException(String stringDate) {
        super("Illegal date: [" + stringDate + "]. Correct form: [" + Rate.DATE_FORMAT_PATTERN + "]");
    }

    @Override
    public String getErrorTextMessage() {
        return getMessage();
    }
}
