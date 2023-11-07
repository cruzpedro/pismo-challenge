package com.pismo.transaction.exception;

public class TransactionNoResultException extends RuntimeException{
    public TransactionNoResultException(String message) {
        super(message);
    }
}
