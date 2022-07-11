package com.tcs.bookreview.service;

import org.springframework.ui.ModelMap;

import static com.tcs.bookreview.service.ConstantsService.ERROR_MESSAGE;

public class ControllerService {

    public static void clearErrors(ModelMap model){
        model.put(ERROR_MESSAGE, null);
    }

}
