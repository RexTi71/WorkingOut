package com.workingout.workingout.controllers;

import com.workingout.workingout.dto.UserDTO;
import com.workingout.workingout.service.UserLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {
    private final UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService){
        this.userLoginService = userLoginService;
    }

    private void prepareLoginPage(Model model){
        model.addAttribute("user", new UserDTO());
    }
    @GetMapping("/login")
    public String getLogin(Model model){
        prepareLoginPage(model);
        return "login";
    }

    @PostMapping("/login")
    public Object validateLogin(UserDTO user, Model model){
        if(userLoginService.isThereAUserByThisUsernameAndPassword(user)){
            return ResponseEntity.ok()
                    .header("HX-Redirect","/")
                    .build();
        }
        prepareLoginPage(model);
        model.addAttribute("loginFail", true);
        return "login :: #login-box-wrapper";
    }
}
