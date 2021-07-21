package vico.WasteManagement.exception;

public class InvalidUserIdException extends RuntimeException{

    public InvalidUserIdException(String message) {
        super(message);
    }

    public InvalidUserIdException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
