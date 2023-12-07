package com.myblog1.exception;

import com.myblog1.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundHandler(ResourceNotFound e , WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(),request.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
