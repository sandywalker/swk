package com.wenku.user.web;

import com.wenku.define.Result;
import com.wenku.security.Guard;
import com.wenku.security.filter.UserOnLineFilter;
import com.wenku.user.model.ActivateResult;
import com.wenku.user.model.User;
import com.wenku.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by sandy on 04/07/2017.
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private Guard guard;

    @GetMapping("/register")
    public String register(){
        return "user/register";
    }

    @GetMapping("/register/check/email")
    @ResponseBody
    public Boolean emailExists(String mail){
        return  !userService.isEmailExists(mail);
    }

    @PostMapping("/register")
    public String doRegister(String mail, String password, Model model){
        Result result =  userService.registerEmail(mail,password);
        model.addAttribute("result",result);
        return "user/regResult";
    }

    @RequestMapping(value="/register/activate")
    public String activate(String mail,String activateCode, Model model){
        ActivateResult result = userService.activeMail(mail,activateCode);
        model.addAttribute("mail",mail);
        model.addAttribute("result",result);
        return "user/activeResult";
    }

    @RequestMapping(value="/register/sendMail")
    @ResponseBody
    public String sendMail(String mail){
        User user = userService.findUserByEmail(mail);
        if (user == null){
            return "false";
        }else{
            userService.sendActivateEmail(user);
            return "success";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/login")
    public String doLogin(String loginName,String password,Model model,HttpServletRequest request){
        Result result = userService.validateLogin(loginName,password);
        if (result.isSuccess()){
            Long uid = userService.findUserIdByLoginName(loginName);
            guard.auth(request,uid);
            return "redirect:/user/my";
        }else {
            model.addAttribute("result",result);
            model.addAttribute("loginName",loginName);
            return "user/login";
        }
    }

    @RequestMapping("/logout")
    public String doLogout(HttpServletRequest request){
        guard.unAuth(request);
        return "redirect:/";
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
