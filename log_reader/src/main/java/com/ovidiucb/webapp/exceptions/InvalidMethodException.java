package com.ovidiucb.webapp.exceptions;

/**
 * Created by ovidiucb
 */
public class InvalidMethodException extends RuntimeException {
    private static final String MESSAGE = "You can upload a file by posting to this same URL.";

    public InvalidMethodException() {
        super(MESSAGE);
    }
}
