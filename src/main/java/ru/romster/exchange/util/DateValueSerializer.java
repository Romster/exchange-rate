package ru.romster.exchange.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.romster.exchange.model.Rate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by romster on 29.09.15.
 */
public class DateValueSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        DateFormat df = new SimpleDateFormat(Rate.DATE_FORMAT_PATTERN);
        jsonGenerator.writeString(df.format(date));
    }
}
