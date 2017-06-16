package com.wenku.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sandy on 16/06/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @RequestMapping("adminHome")
    public String adminUserHome(){
        return "admin/user/admins";
    }
}
