package com.jewellery.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler implements GlobalExceptionHandlerExc {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> ExceptionHandler(Exception ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ApiResponse> userNotFoundExceptionHandler(UserNotFoundException ex) {
//        String message = ex.getMessage();
//        ApiResponse apiResponse = new ApiResponse(message, false);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
//    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( (error) ->{
        String fieldName = ((FieldError)error).getField();
        String message = error.getDefaultMessage();
        resp.put(fieldName,message);
        });
        return new ResponseEntity<Object>(resp, HttpStatus.BAD_REQUEST);
    }

}
