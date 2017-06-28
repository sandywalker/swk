package com.wenku.doc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sandy on 27/06/2017.
 */
@Controller
public class SearchController {


    @RequestMapping("search")
    public String search(){
        return "search";
    }
}
