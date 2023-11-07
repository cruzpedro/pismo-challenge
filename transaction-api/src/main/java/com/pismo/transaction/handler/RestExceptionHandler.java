package com.pismo.transaction.handler;

import com.pismo.transaction.exception.TransactionException;
import com.pismo.transaction.exception.TransactionNoResultException;
import com.pismo.transaction.handler.payload.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({TransactionNoResultException.class})
    protected ResponseEntity<Object> handleException(TransactionNoResultException ex) {
        return buildResponseEntity(ApiError.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TransactionException.class})
    protected ResponseEntity<Object> handleException(TransactionException ex) {
        return buildResponseEntity(ApiError.builder()
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus httpStatus) {
        return new ResponseEntity(apiError, httpStatus);
    }

}
