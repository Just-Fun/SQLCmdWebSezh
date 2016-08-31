package ua.com.juja.sqlcmd.model;

/**
 * Created by max on 13.10.2015.
 */
public class PGException extends RuntimeException {

    public PGException(String message, Exception exception) {
        super(message, exception);
    }

}
