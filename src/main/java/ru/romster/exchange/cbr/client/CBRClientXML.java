package ru.romster.exchange.cbr.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.romster.exchange.exception.CBRParseXMLException;
import ru.romster.exchange.exception.ExchangeRateApplicationException;
import ru.romster.exchange.exception.NoDataFoundException;
import ru.romster.exchange.exception.UnknownCharCodeException;
import ru.romster.exchange.model.Rate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by romster on 29.09.15.
 */
public class CBRClientXML implements CBRClient {

    private static String XML_DAILY_URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=%s";
    private static String CBR_DATE_FORMAT = "dd/MM/yyyy";
    private static String ENCODING = "Windows-1251";

    @Override
    public Rate getRate(String code, Date date) throws ExchangeRateApplicationException {
        try {
            BigDecimal rateValue = getDailyRateValue(code, date);
            return new Rate(code, rateValue, date);
        } catch (IOException ex) {
            throw new ExchangeRateApplicationException(ex);
        }
    }

    @Override
    public Rate getRate(String code) throws ExchangeRateApplicationException {
        try {
            Date currDate = new Date();
            BigDecimal rateValue = getDailyRateValue(code, currDate);
            return new Rate(code, rateValue, currDate);
        } catch (IOException ex) {
            throw new ExchangeRateApplicationException(ex);
        }
    }


    private BigDecimal getDailyRateValue(String code, Date date) throws IOException,
            CBRParseXMLException, UnknownCharCodeException, NoDataFoundException {
        DateFormat sdf = new SimpleDateFormat(CBR_DATE_FORMAT);
        String dateParameter = sdf.format(date);
        String requestUrl = String.format(XML_DAILY_URL, dateParameter);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(requestUrl);
        HttpResponse response = client.execute(request);
        try (InputStreamReader reader =
                     new InputStreamReader(response.getEntity().getContent(), Charset.forName(ENCODING))) {
            BigDecimal result = parseResponseString(code, reader);
            if (result == null) {
                throw new UnknownCharCodeException(code);
            }
            return result;
        }
    }

    private BigDecimal parseResponseString(String code, Reader xmlReader) throws CBRParseXMLException, NoDataFoundException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource(xmlReader);
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();

            NodeList elements = doc.getElementsByTagName("Valute");
            if (elements.getLength() == 0) {
                throw new NoDataFoundException();
            }
            for (int i = 0; i < elements.getLength(); i++) {
                Node valuteNode = elements.item(i);
                if (valuteNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) elements.item(i);
                    String charCode = element.getElementsByTagName("CharCode").item(0).getTextContent();
                    if (code.equalsIgnoreCase(charCode)) {
                        String nominalString = element.getElementsByTagName("Nominal").item(0).getTextContent();
                        BigDecimal nominal = new BigDecimal(nominalString);
                        String rateString = element.getElementsByTagName("Value").item(0).getTextContent();
                        BigDecimal result = new BigDecimal(rateString.replace(",", "."));
                        result = result.divide(nominal);
                        return result;
                    }
                }
            }
            return null;
        } catch (NoDataFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CBRParseXMLException(ex);
        }
    }
}
