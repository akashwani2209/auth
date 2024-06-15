package com.example.paymentsLogin.Controller;

import com.example.paymentsLogin.Model.User;
import com.example.paymentsLogin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }
}
