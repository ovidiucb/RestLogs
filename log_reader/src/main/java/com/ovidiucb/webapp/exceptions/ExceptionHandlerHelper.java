package com.ovidiucb.webapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ovidiucb
 */
@ControllerAdvice
@EnableWebMvc
public abstract class ExceptionHandlerHelper {
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such IP address") //404
    @ExceptionHandler(IpNotFoundException.class)
    public @ResponseBody String handleResourceNotFoundException(IpNotFoundException ex) {
        return ex.getLocalizedMessage();
    }

    @ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Invalid user") //401
    @ExceptionHandler(InvalidUserException.class)
    public @ResponseBody String handleInvalidUserException(InvalidUserException ex) {
        return ex.getLocalizedMessage();
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad request") //400
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody String handleBadRequestException(BadRequestException ex) {
        return ex.getLocalizedMessage();
    }

    @ResponseStatus(value= HttpStatus.METHOD_NOT_ALLOWED, reason="You can upload a file by posting to this same URL.") //400
    @ExceptionHandler(InvalidMethodException.class)
    public @ResponseBody String handleInvalidMethodException(InvalidMethodException ex) {
        return ex.getLocalizedMessage();
    }
}

