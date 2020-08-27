package Utilities.seleniumcustomframework.extension.exceptions;

public class RetryException extends RuntimeException {
    public RetryException(Exception exceptionThrown){
        super(exceptionThrown);
    }
}
