package ru.romster.exchange.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by romster on 29.09.15.
 */
public class RateValueSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal rateValue, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(rateValue.toString());

    }
}
