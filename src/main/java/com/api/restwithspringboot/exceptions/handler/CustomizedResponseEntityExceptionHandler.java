package com.api.restwithspringboot.exceptions.handler;


import com.api.restwithspringboot.exceptions.ExceptionResponse;
import com.api.restwithspringboot.exceptions.UnsupportedMathOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * ControllerAdvice: Toda vez que um controller
 * lançar uma excessão, caso não exista um tratamento
 * mais específico, vai cair no controller global advice
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception exception,
            WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(UnsupportedMathOperationException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(
            Exception exception,
            WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

    }

}
