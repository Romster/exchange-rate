package ru.romster.exchange.exception;

/**
 * Created by romster on 29.09.15.
 */
public class NoDataFoundException extends  ExchangeRateApplicationException{
    @Override
    public String getErrorTextMessage() {
        return "No data found";
    }
}
