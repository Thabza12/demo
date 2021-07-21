package vico.WasteManagement.exception;

public class InvalidException extends RuntimeException{

    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
