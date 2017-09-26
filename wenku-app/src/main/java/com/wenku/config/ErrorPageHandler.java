package com.wenku.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by sandy on 25/09/2017.
 */
@ControllerAdvice
public class ErrorPageHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(Exception ex) {
        return "errors/404";
    }

    @ExceptionHandler(Exception.class)
    public String handle500(Exception ex) {
        return "errors/500";
    }

}
