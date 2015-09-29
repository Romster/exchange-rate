package ru.romster.exchange.controller.rest;

import org.springframework.web.bind.annotation.*;
import ru.romster.exchange.cbr.client.CBRClient;
import ru.romster.exchange.cbr.client.CBRClientXML;
import ru.romster.exchange.exception.ExchangeRateApplicationException;
import ru.romster.exchange.exception.InvalidDateFormatException;
import ru.romster.exchange.model.Error;
import ru.romster.exchange.model.Rate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by romster on 29.09.15.
 */
@RestController
public class ExchangeRateController {


    @RequestMapping(method = RequestMethod.GET, value = "/api/rate/{code}/{date}")
    public Rate getRate(@PathVariable("code")
                        String code,
                        @PathVariable("date")
                        String date) throws ExchangeRateApplicationException {
        CBRClient clent = getCbrClient();
        Rate r = getCbrClient().getRate(code, parseDate(date));
        return r;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/rate/{code}")
    public Rate getRate(@PathVariable("code")
                        String code) throws ExchangeRateApplicationException {
        Rate r = getCbrClient().getRate(code);
        return r;

    }

    @ExceptionHandler(ExchangeRateApplicationException.class)
    public Error handleError(ExchangeRateApplicationException exception) {
        return new Error(exception.getErrorTextMessage());
    }


    private CBRClient getCbrClient() {
        return new CBRClientXML();
    }

    private Date parseDate(String dateString) throws InvalidDateFormatException {
        DateFormat df = new SimpleDateFormat(Rate.DATE_FORMAT_PATTERN);
        try {
            Date date = df.parse(dateString);
            return date;
        } catch (ParseException e) {
            throw new InvalidDateFormatException(dateString);
        }
    }
}
