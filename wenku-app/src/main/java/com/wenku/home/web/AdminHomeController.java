package com.wenku.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sandy on 03/06/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminHomeController {


    @RequestMapping
    public  String index(){
        return "admin/index";
    }

    @RequestMapping("/dashboard")
    public  String dashboard(){
        return "admin/dashboard";
    }


}
