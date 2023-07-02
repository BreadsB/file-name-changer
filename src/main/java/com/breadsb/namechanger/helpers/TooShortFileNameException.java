package com.breadsb.namechanger.helpers;

public class TooShortFileNameException extends RuntimeException {
    public TooShortFileNameException(String errorMessage) {
        super(errorMessage);
    }
}