package org.tanglizi.oyster.front.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tanglizi.oyster.front.exceptions.PageNotFoundException;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler({PageNotFoundException.class})
    public String handle(HttpServletResponse response, PageNotFoundException e){
        // response.setStatus(404);

        return "error_404";
    }
}
