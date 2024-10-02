package com.microfvn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping("/")
    public String showRegistrationForm() {
        return "index";
    }

    @PostMapping("/")
    public ModelAndView registerUser(@RequestParam String userName,
                                     @RequestParam String password,
                                     @RequestParam String confirmPassword,
                                     @RequestParam String fullName,
                                     @RequestParam Boolean gender,
                                     @RequestParam String address,
                                     @RequestParam String email,
                                     @RequestParam String telephone) {
        if (!password.equals(confirmPassword)) {
            return new ModelAndView("index", "message", "Mật khẩu không khớp");
        }

        // Save user logic
        // User user = new User(null, userName, password, true, fullName, gender, address, email, telephone, null);
        // userService.save(user);

        return new ModelAndView("index", "message", "Đăng ký thành công");
    }
}

