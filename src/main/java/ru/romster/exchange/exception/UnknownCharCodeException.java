package ru.romster.exchange.exception;

/**
 * Created by romster on 29.09.15.
 */
public class UnknownCharCodeException extends ExchangeRateApplicationException {

    private String code;

    public UnknownCharCodeException(String code) {
        super("Unknown currency code : [" + code+"]");
        this.code = code;
    }

    @Override
    public String getErrorTextMessage() {
        return getMessage();
    }
}
