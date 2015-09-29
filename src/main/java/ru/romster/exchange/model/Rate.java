package ru.romster.exchange.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.romster.exchange.util.RateValueSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by romster on 29.09.15.
 */
public class Rate {

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    private String code;
    BigDecimal rate;
    Date date;

    public Rate(String code, BigDecimal rate, Date date) {
        this.code = code;
        this.rate = rate;
        this.date = date;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
    public Date getDate() {
        return date;
    }


    @JsonSerialize(using = RateValueSerializer.class)
    public BigDecimal getRate() {
        return rate;
    }

    public String getCode() {
        return code;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rate rate1 = (Rate) o;

        if (!code.equals(rate1.code)) return false;
        if (!rate.equals(rate1.rate)) return false;
        return date.equals(rate1.date);

    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + rate.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
