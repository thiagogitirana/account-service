package com.pismo.service.account.domain.exceptions;

public class LimitException extends RuntimeException {
    public LimitException(String message) {
        super(message);
    }
}
