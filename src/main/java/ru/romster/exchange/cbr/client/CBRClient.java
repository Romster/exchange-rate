package ru.romster.exchange.cbr.client;

import ru.romster.exchange.exception.ExchangeRateApplicationException;
import ru.romster.exchange.model.Rate;

import java.util.Date;

/**
 * Created by romster on 29.09.15.
 */
public interface CBRClient {

    public abstract Rate getRate(String code, Date date) throws
            ExchangeRateApplicationException;


    /**
     * Get today's currency rate rom CBR
     *
     * @param code - currency code
     * @return
     */
    public abstract Rate getRate(String code) throws
            ExchangeRateApplicationException;

}
