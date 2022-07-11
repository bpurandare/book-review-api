package com.tcs.bookreview.controller;

import com.tcs.bookreview.model.User;
import com.tcs.bookreview.security.UserDetailImpl;
import com.tcs.bookreview.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import static com.tcs.bookreview.service.ConstantsService.*;
import static com.tcs.bookreview.service.ControllerService.clearErrors;

@Controller
@SessionAttributes({ATTRIBUTE_USER, ATTRIBUTE_LOGIN_RESULT})
public class AuthController {

    @Autowired
    private IUserService userService;

    @ModelAttribute(ATTRIBUTE_USER)
    public User user(){
        return null;
    }

    @ModelAttribute(ATTRIBUTE_LOGIN_RESULT)
    public boolean isLoginSuccess(){
        return false;
    }

    @RequestMapping("/login")
    public String loginform(@ModelAttribute(ATTRIBUTE_USER) User user, ModelMap modelMap){
        clearErrors(modelMap);
        modelMap.put(ATTRIBUTE_USER, new User());
        return "login";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(@ModelAttribute(ATTRIBUTE_USER) User user, @ModelAttribute(ATTRIBUTE_LOGIN_RESULT) boolean isLoginSuccess, ModelMap modelMap){
        clearErrors(modelMap);
        UserDetailImpl userDetail = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDetail.getUser();
        isLoginSuccess = true;
        modelMap.put(ATTRIBUTE_USER, user);
        modelMap.put(ATTRIBUTE_LOGIN_RESULT, isLoginSuccess);
        return "redirect:/";
    }

    @RequestMapping("/loginFailure")
    public String loginFail(ModelMap modelMap){
        modelMap.put(ATTRIBUTE_USER, new User());
        modelMap.put(ATTRIBUTE_LOGIN_RESULT, false);
        modelMap.put(ERROR_MESSAGE, "Login Failed!");
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(ModelMap modelMap){
        modelMap.put(ATTRIBUTE_USER, new User());
        modelMap.put(ATTRIBUTE_LOGIN_RESULT, false);
        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute(ATTRIBUTE_USER) User user, ModelMap modelMap){
        modelMap.put(ATTRIBUTE_USER, user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute(ATTRIBUTE_USER) User user, ModelMap modelMap){

        clearErrors(modelMap);
        String username = user.getUsername();
        user = userService.registerUser(user);

        if(user==null){
            modelMap.put(ERROR_MESSAGE, "User with name \""+username+"\" already exits!");
            return "register";
        }

        return "login";
    }

}
