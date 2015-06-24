package com.ovidiucb.webapp.exceptions;

/**
 * Created by ovidiucb
 */
public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String user) {
        super("Invalid user: " + user);
    }
}
