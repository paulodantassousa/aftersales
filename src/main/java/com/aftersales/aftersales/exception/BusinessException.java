package com.aftersales.aftersales.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BusinessException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PaymentsException.class)
    public ResponseEntity<?> paymentException(PaymentsException paymentsException){
        var details = new ExceptionDetails();
        details.setMessage(paymentsException.getMessage());
        details.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(details, HttpStatus.UNPROCESSABLE_ENTITY);
    }







}
