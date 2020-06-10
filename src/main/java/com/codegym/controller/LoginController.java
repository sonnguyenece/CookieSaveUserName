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

//    @ModelAttribute
//    public User setUpForm() {
//        return new User();
//    }
//
//    @RequestMapping
//    public String Index(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
//        Cookie cookie = new Cookie("setUser", setUser);
//        model.addAttribute("cookieValue", cookie);
//        return "/login";
//    }
//
//    @PostMapping("/dologin")
//    public String doLogin(@ModelAttribute("user") User user, Model model, @CookieValue(value = "setUser", defaultValue = "")
//            String setUser, HttpServletResponse response, HttpServletRequest request) {
//        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("12345")) {
//            if (user.getEmail() != null)
//                setUser = user.getEmail();
//
//            Cookie cookie =new Cookie("setUser",setUser);
//            cookie.setMaxAge(60);
//            response.addCookie(cookie);
//
//            Cookie[]cookies = request.getCookies();
//            for (Cookie ck:cookies){
//                if (ck.getName().equals("setUser")){
//                    model.addAttribute("cookieValue",ck);
//                    break;
//                }
//                else {
//                    ck.setValue("");
//                    model.addAttribute("cookieValue",ck);
//                    break;
//                }
//            }
//            model.addAttribute("message","Login success. Welcome");
//
//        }else{
//            user.setEmail("");
//            Cookie cookie =new Cookie("setUser",setUser);
//            model.addAttribute("cookieValue",cookie);
//            model.addAttribute("message","Login Failed. Try again.");
//        }
//        return "/login";
//    }

    @ModelAttribute
    public User setUpForm() {
        return new User();
    }
//
//    @GetMapping("/")
//    public String getSigninForm() {
//        return "/login";
//    }
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
    public String doSignin(@ModelAttribute User user, HttpServletRequest httpServletRequest, Model model,
                           HttpServletResponse httpServletResponse,@RequestParam("email")String email,
                           @RequestParam("password")String password) {

        user.setEmail(email);
        user.setPassword(password);
        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("12345")) {
            Cookie emailSave = new Cookie("email", user.getEmail());
            Cookie passwordSave = new Cookie("password", user.getPassword());

            httpServletResponse.addCookie(emailSave);
            httpServletResponse.addCookie(passwordSave);
            model.addAttribute("emailSave",email);
            model.addAttribute("passwordSave",password);
            model.addAttribute("message", "Login success. Welcome");

            return "/login";
        } else {
            model.addAttribute("message", "Login Failed. Try again.");
        }
        return "/fail";
    }

}
