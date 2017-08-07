package com.wenku.doc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by sandy on 04/07/2017.
 */
@Controller
public class UserController {

    @GetMapping("/register")
    public String register(){
        return "user/register";
    }

    @PostMapping("/register")
    public String doRegister(){
        return "user/regResult";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/login")
    public String doLogin(){
        return "redirect:/user/my";
    }


    @GetMapping("user/my")
    public String mySpace(){

        return "user/my";
    }

    @GetMapping("user/upload")
    public String upload(){

        return "user/upload";
    }

    @GetMapping("user/orders")
    public String orders(){

        return "user/orders";
    }

    @GetMapping("user/setting")
    public String setting(){

        return "user/setting";
    }


}
