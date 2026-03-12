package com.workingout.workingout.controllers;

import com.workingout.workingout.dto.UserDTO;
import com.workingout.workingout.exceptions.InputNotValidException;
import com.workingout.workingout.models.User;
import com.workingout.workingout.service.UserLoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {
    private final UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService){
        this.userLoginService = userLoginService;
    }

    @PostMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("loginFail", true);
        model.addAttribute("errorMsg", "Nieprawidłowy login lub hasło");

        return "login :: #login-box-wrapper";
    }
    @PostMapping("/register")
    public Object validateRegister(@Valid UserDTO userDto, BindingResult bindingResult, Model model) {
        userLoginService.addUserToDb(userDto);

        model.addAttribute("registerSuccess", true);
        return "login :: #login-box-wrapper";
    }
    private void prepareLoginPage(Model model){
        model.addAttribute("user", new UserDTO());
    }
    @GetMapping("/login")
    public String getLogin(Model model){
        prepareLoginPage(model);
        return "login";
    }
//    private void throwAlertBox(Model model, String message){
//        model.addAttribute("loginFail", true);
//        model.addAttribute("errorMsg", message);
//    }
//    @PostMapping("/login")
//    public Object validateLogin(UserDTO user, HttpSession session, Model model){
//        if(userLoginService.isThereAUserByThisUsernameAndPassword(user)){
//            //session.setAttribute("loggedUser", user);
//            return ResponseEntity.ok()
//                    .header("HX-Redirect","/")
//                    .build();
//        }
//        prepareLoginPage(model);
//        throwAlertBox(model, "Failed to login");
//        return "login :: #login-box-wrapper";
//    }
//    @PostMapping("/register")
//    public Object validateRegister(@Valid UserDTO user, BindingResult bindingResult, Model model){
//        if(bindingResult.hasErrors()){
//            throw new InputNotValidException();
//        }
//        prepareLoginPage(model);
//        if(userLoginService.isThereAUserByUsername(user.getUsername())){
//            throwAlertBox(model, "User with given username already exists");
//            return "login :: #login-box-wrapper";
//        }
//        try{
//            userLoginService.addUserToDb(user);
//        }catch (IllegalArgumentException ex){
//            throwAlertBox(model,"Given username or password was empty");
//            return "login :: #login-box-wrapper";
//        }
//        model.addAttribute("registerSuccess", true);
//        return "login :: #login-box-wrapper";
//    }

}
