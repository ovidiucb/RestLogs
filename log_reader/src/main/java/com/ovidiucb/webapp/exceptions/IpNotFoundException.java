package com.ovidiucb.webapp.exceptions;

/**
 * Created by ovidiucb
 */
public class IpNotFoundException extends RuntimeException {
    public IpNotFoundException(String ipAddress) {
        super("No such IP address: " + ipAddress);
    }
}
