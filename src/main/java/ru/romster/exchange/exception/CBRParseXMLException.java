package ru.romster.exchange.exception;

/**
 * throw  if somethinf went wrong with response xml parsing
 * Created by romster on 29.09.15.
 */
public class CBRParseXMLException extends ExchangeRateApplicationException {
    public CBRParseXMLException(String message) {
        super(message);
    }

    public CBRParseXMLException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getErrorTextMessage() {
        return "Exception while parsing CBR XML data.";
    }
}
