package com.wenku.user.web;

import com.wenku.define.Result;
import com.wenku.user.model.AdminAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wenku.user.model.AdminUser;
import com.wenku.user.service.AdminUserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 后台管理员登录
 * Created by sandy on 15/06/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminAuth adminAuth;

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("login")
    public String adminLogin(){
        return "admin/login";
    }

    @PostMapping("login")
    public String doAdminLogin(HttpServletRequest request,String username, String password, RedirectAttributes attr){
        Result result = adminAuth.auth(username,password);
        if (result.isSuccess()){
            request.getSession().setAttribute(AdminAuth.ADMIN_SESSION_ATTR,username);
            return "redirect:/admin";
        }else {
            attr.addFlashAttribute("error",result.getMessage());
            return "redirect:/admin/login";
        }

    }
}
