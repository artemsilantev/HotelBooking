package eu.senla.hotelBooking.controllers;

import eu.senla.hotelBooking.exceptions.NoRecordException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsResolverController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoRecordException.class)
    protected ResponseEntity<Object> handleNoRecordException(NoRecordException ex, WebRequest request) {
        String message = String.format("No %s, with requested id: %s", ex.getEntityType(), ex.getId());
        return handleExceptionInternal(ex, buildErrorResponseBody("Not found", message),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private Map<String, String> buildErrorResponseBody(String errorTitle, String message) {
        return new HashMap<String, String>() {
            {
                put("error", errorTitle);
                put("message", message);
            }
        };
    }

}