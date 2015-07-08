package com.ovidiucb.webapp.exceptions;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ovidiucb
 */
public abstract class ExceptionHandlerHelper implements ErrorController {
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such IP address") //404
    @ExceptionHandler(IpNotFoundException.class)
    @ResponseBody
    public String handleResourceNotFoundException(IpNotFoundException ex) {
        return ex.getLocalizedMessage();
    }

    @ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Invalid user") //401
    @ExceptionHandler(InvalidUserException.class)
    @ResponseBody
    public String handleInvalidUserException(InvalidUserException ex) {
        return ex.getLocalizedMessage();
    }

    @ResponseStatus(value= HttpStatus.METHOD_NOT_ALLOWED, reason="You can upload a file by posting to this same URL.") //400
    @ExceptionHandler(InvalidMethodException.class)
    @ResponseBody
    public String handleInvalidMethodException(InvalidMethodException ex) {
        return ex.getLocalizedMessage();
    }
}

