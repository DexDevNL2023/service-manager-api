package net.service.manager.about.generic.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import net.service.manager.about.generic.dto.reponse.RessourceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

    //handler specific exceptions
    @ExceptionHandler(RessourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(RessourceNotFoundException exception, WebRequest request, Locale locale) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(new RessourceResponse(false, errorDetails), HttpStatus.NOT_FOUND);
    }

    //handler Badrequest
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBadAttribeExceptionException(HttpClientErrorException.BadRequest exception, WebRequest request, Locale locale) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(new RessourceResponse(false, errorDetails), HttpStatus.BAD_REQUEST);
    }

    //handler global exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request, Locale locale) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(new RessourceResponse(false, errorDetails), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request, Locale locale) {
        List<ErrorDetails> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            ErrorDetails errorDetails = new ErrorDetails(errorMessage, request.getDescription(false));
            errors.add(errorDetails);
        });
        return new ResponseEntity<>(new RessourceResponse(false, errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, WebRequest request, Locale locale) {
        String errorDetails = "JSON inacceptable " + exception.getMessage();
        if (exception.getCause() instanceof InvalidFormatException ifx) {
            if (ifx.getTargetType() != null) {
                if (ifx.getTargetType().isEnum()) {
                    errorDetails = String.format("Valeur d'énumération invalide :'%s' pour '%s'. La valeur doit être l'une parmi les suivantes : %s.",
                            ifx.getValue(), ifx.getPath().get(ifx.getPath().size() - 1).getFieldName(), Arrays.toString(ifx.getTargetType().getEnumConstants()));
                }
            }
        }
        return new ResponseEntity<>(new RessourceResponse(false, errorDetails), HttpStatus.BAD_REQUEST);
    }
}
