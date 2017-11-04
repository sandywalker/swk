package com.wenku.user.web;

import com.wenku.define.Result;
import com.wenku.doc.manager.UploadProcessor;
import com.wenku.doc.service.DocService;
import com.wenku.security.Guard;
import com.wenku.user.model.ActivateResult;
import com.wenku.user.model.User;
import com.wenku.user.service.UserService;
import com.wenku.util.MVCUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by sandy on 04/07/2017.
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private Guard guard;

    @Resource
    private DocService docService;

    @Resource
    private UploadProcessor uploadProcessor;

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
    public String doLogin(String loginName, String password, boolean remember, Model model,
                          HttpServletRequest request, HttpServletResponse response){
        Result result = userService.validateLogin(loginName,password);
        if (result.isSuccess()){
            Long uid = userService.findUserIdByLoginName(loginName);
            Guard.auth(request,response,uid,remember);
            return "redirect:/user/my";
        }else {
            model.addAttribute("result",result);
            model.addAttribute("loginName",loginName);
            return "user/login";
        }
    }

    @RequestMapping("/logout")
    public String doLogout(HttpServletRequest request,HttpServletResponse response){
        Guard.unAuth(request,response);
        return "redirect:/";
    }


    @GetMapping("user/my")
    public String mySpace(){

        return "user/my";
    }



    @GetMapping("user/orders")
    public String orders(){

        return "user/orders";
    }

    @GetMapping("user/setting")
    public String setting(HttpServletRequest request,Model model){
        Long uid = Guard.getUID(request);
        User user = userService.findOne(uid);
        model.addAttribute("user",user);
        return "user/setting";
    }

    @GetMapping("user/cpass")
    public String changePass(){
        return "user/cpass";
    }

    @PostMapping("user/cpass")
    public String updatePassword(HttpServletRequest request,String password,String newPass,RedirectAttributes rattr){
        Assert.hasText(password,"密码不能为空！");
        Assert.hasText(newPass,"新密码不能为空！");
        Long uid = Guard.getUID(MVCUtils.getCurrentRequest());
        User user = userService.findOne(uid);
        String enpass = userService.encryptPassword(user,password);
        boolean valid = enpass.equals(user.getPass());
        if (valid){
            String newEnpass = userService.encryptPassword(user,newPass);
            user.setPass(newEnpass);
            user.setUpdateDate(new Date());
            userService.update(user);
            rattr.addFlashAttribute("message","密码修改成功！");

        }else{
           rattr.addFlashAttribute("error","密码修改失败！");
        }
        return "redirect:/user/cpass";
    }

    @PostMapping("user/vpass")
    @ResponseBody
    public Boolean validatePassword(String pass){
        Assert.hasText(pass,"密码不能为空！");
        Long uid = Guard.getUID(MVCUtils.getCurrentRequest());
        User user = userService.findOne(uid);
        String enpass = userService.encryptPassword(user,pass);
        return enpass.equals(user.getPass());
    }

    @PostMapping("user/setting")
    public String updateSetting(User user, HttpServletRequest request, RedirectAttributes rattr){
        Long uid = Guard.getUID(request);
        user.setId(uid);
        user.setStatus(null);
        user.setUpdateDate(new Date());
        userService.update(user);
        rattr.addFlashAttribute("message","信息已更新！");
        return "redirect:/user/setting";
    }


}
