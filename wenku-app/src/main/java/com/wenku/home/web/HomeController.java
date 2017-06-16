package com.wenku.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sandy on 03/06/2017.
 */
@Controller
public class HomeController {


    @RequestMapping("/")
    public  String index(){

        return "index";
    }


}
