package vico.WasteManagement.exception;

public class VehicleException extends RuntimeException{

    public VehicleException(String message) {
        super(message);
    }

    public VehicleException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
