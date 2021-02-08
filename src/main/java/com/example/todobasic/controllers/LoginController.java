package com.example.todobasic.controllers;

import com.example.todobasic.dto.Usedto;
import com.example.todobasic.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


/**
 * Created on February, 2021
 *
 * @author tolga
 */

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new Usedto());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("user") @Valid Usedto usedto,
                                  BindingResult result) {
        logger.info("New user {}", usedto);

        if (result.hasErrors()) {
            return "register";
        }

        if (!usedto.getPassword().equals(usedto.getMatchingPassword())) {
            result.rejectValue("password", "", "Password not matching");
            return "register";
        }

        userService.create(usedto);

        return "redirect:/login";
    }
}
