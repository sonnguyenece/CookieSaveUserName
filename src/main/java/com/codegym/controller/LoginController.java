package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/")
@Controller
public class LoginController {

        @RequestMapping
    public String Index(@CookieValue(defaultValue = "") String email,
                        @CookieValue(defaultValue = "") String password,Model model)
        //Do ten cookie trung vs ten trong String nen co the ko viet value="password"
    {
        model.addAttribute("emailSave", email);
        model.addAttribute("passwordSave", password);
        return "/login";
    }

    @PostMapping("/signin")
    public String doSignin(User user, HttpServletRequest httpServletRequest, Model model,
                           HttpServletResponse httpServletResponse,@RequestParam("email")String email,
                           @RequestParam("password")String password) {

        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("12345")) {
            Cookie emailSave = new Cookie("email", user.getEmail());
            Cookie passwordSave = new Cookie("password", user.getPassword());

            httpServletResponse.addCookie(emailSave);
            httpServletResponse.addCookie(passwordSave);

            model.addAttribute("emailSave",emailSave.getValue());
            model.addAttribute("passwordSave",passwordSave.getValue());
            model.addAttribute("message", "Login success. Welcome");

            return "/login";
        } else {
            model.addAttribute("message", "Login Failed. Try again.");
        }
        return "/fail";
    }

}
