package com.workingout.workingout.controllers;

import com.workingout.workingout.dto.UserDTO;
import com.workingout.workingout.exceptions.InputNotValidException;
import com.workingout.workingout.exceptions.RegistrationException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserLoginController {
    private final UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService){
        this.userLoginService = userLoginService;
    }

    private void throwAlertBox(RedirectAttributes attributes, String message){
        attributes.addFlashAttribute("loginFail", true);
        attributes.addFlashAttribute("errorMsg", message);
    }
    @PostMapping("/login-error")
    public String loginError(Model model) {
        prepareLoginPage(model);
        model.addAttribute("loginFail", true);
        model.addAttribute("errorMsg", "Invalid login or password");

        return "login :: #login-box-wrapper";
    }
    @PostMapping("/register")
    public String validateRegister(@Valid UserDTO userDto, BindingResult bindingResult, RedirectAttributes attributes) {
        try{
            if(bindingResult.hasErrors()){
                throwAlertBox(attributes, "Username must be between 3 and 24,<br>and password between 6 and 24");
                return "redirect:/login";
            }
            userLoginService.addUserToDb(userDto);
            attributes.addFlashAttribute("registerSuccess", true);
            return "redirect:/login";
        }catch (RegistrationException ex){
            throwAlertBox(attributes, "Failed to register user");
            return "redirect:/login";
        }
    }
    private void prepareLoginPage(Model model){
        model.addAttribute("user", new UserDTO());
    }
    @GetMapping("/login")
    public String getLogin(Model model){
        prepareLoginPage(model);
        return "login";
    }


}
