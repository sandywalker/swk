package com.wenku.home.web;

import com.wenku.security.Guard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by sandy on 03/06/2017.
 */
@Controller
public class HomeController {


    @Resource
    private Guard guard;

    @RequestMapping("/")
    public  String index(){

        return "index";
    }

    @RequestMapping("/topinfo")
    public String topInfo(HttpServletRequest request,Model model){
        model.addAttribute("logined",guard.isLogined(request));
        model.addAttribute("user",guard.getLoginUser(request));
        return "common/topinfo";
    }


}
