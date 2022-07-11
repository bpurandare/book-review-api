package com.tcs.bookreview.exception;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static com.tcs.bookreview.service.ConstantsService.ERROR_MESSAGE;

//@ControllerAdvice
public class ErrorHandler {

    //@ExceptionHandler(Throwable.class)
    public String handleGenericBookListErrors(Exception e, ModelMap model){
        String errorMessage = e.getMessage();
        model.put(ERROR_MESSAGE, errorMessage);
        return "booklist";
    }
}
