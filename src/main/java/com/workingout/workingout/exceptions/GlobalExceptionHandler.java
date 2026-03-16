package com.workingout.workingout.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private void addToLog(String error){
        logger.warn(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorObject> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        InputNotValidException newEx = new InputNotValidException(ex.getMessage());
        return handleInputNotValidException(newEx);
    }
    @ExceptionHandler(InputNotValidException.class)
    public ResponseEntity<ErrorObject> handleInputNotValidException(InputNotValidException ex){
        ErrorObject errorObject = new ErrorObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        addToLog(ex.toString());
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<ErrorObject> handleRegistrationException(RegistrationException ex){
        ErrorObject errorObject = new ErrorObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        addToLog(ex.toString());
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }
}
