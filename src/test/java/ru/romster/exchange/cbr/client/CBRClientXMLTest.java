package ru.romster.exchange.cbr.client;

import org.junit.Test;
import ru.romster.exchange.model.Rate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;


/**
 * Created by romster on 29.09.15.
 */
public class CBRClientXMLTest {

    @Test
    public void testGetRateToday() throws Exception {
        CBRClient client = new CBRClientXML();
        Rate rate = client.getRate("USD");
        assertNotNull(rate);
    }

    @Test
    public void testGetRate1() throws Exception {
        CBRClient client = new CBRClientXML();
        String dateString = "08.06.2000";
        DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
        Date date = df.parse(dateString);
        Rate r = client.getRate("uah", date);
        assertNotNull(r);
    }


}