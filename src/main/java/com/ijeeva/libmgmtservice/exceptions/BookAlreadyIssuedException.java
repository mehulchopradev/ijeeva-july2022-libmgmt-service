package com.ijeeva.libmgmtservice.exceptions;

public class BookAlreadyIssuedException extends RuntimeException {
    public BookAlreadyIssuedException(String message) {
        super(message);
    }
}
