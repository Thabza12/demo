package vico.WasteManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidUserIdException.class})
    public ResponseEntity<Object> handleInvalidUserIdException(InvalidUserIdException e){
        //Create payload containing exception details
        //return response entity
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        InvalidUserIdExceptionResponse userIdExceptionResponse = new InvalidUserIdExceptionResponse(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(userIdExceptionResponse, badRequest);
    }

    @ExceptionHandler(value = {VehicleException.class})
    public ResponseEntity<Object> handleVehicleException(VehicleException e){
        VehicleExceptionResponse vehicleExceptionResponse = new VehicleExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(vehicleExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidException.class})
    public ResponseEntity<Object> handleInvalidException(InvalidException e){
        InvalidExceptionResponse invalidExceptionResponse = new InvalidExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(invalidExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
