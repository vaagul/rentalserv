package com.atlantis.rentalserv.utils.exceptions;

public class InvalidTransactionException extends RuntimeException{
    public InvalidTransactionException(String errorMessage){
        super(errorMessage);
    }
}
