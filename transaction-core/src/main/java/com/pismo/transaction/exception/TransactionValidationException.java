package com.pismo.transaction.exception;

public class TransactionValidationException extends RuntimeException{
    public TransactionValidationException(String message) {
        super(message);
    }
}
