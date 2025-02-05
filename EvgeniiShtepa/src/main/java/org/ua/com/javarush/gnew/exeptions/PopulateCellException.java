package org.ua.com.javarush.gnew.exeptions;

public class PopulateCellException extends RuntimeException {
    public PopulateCellException(String message, Throwable cause) {
        super(message, cause);
    }
}
