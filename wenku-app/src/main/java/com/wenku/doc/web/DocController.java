package com.wenku.doc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sandy on 29/06/2017.
 */
@Controller
@RequestMapping("/doc")
public class DocController {

    @RequestMapping("view/{id}")
    public String view(@PathVariable long id){
        return "doc/view";
    }
}
