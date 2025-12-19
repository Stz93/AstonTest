package org.example;

public class DataLoadingException extends Exception{
    public DataLoadingException(String message) {
        super(message);
    }

    public DataLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
//перепроверить если вдруг окажется что чето не то