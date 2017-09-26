package com.wenku.doc.web;

import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.DocTableNames;
import com.wenku.doc.service.DocService;
import com.wenku.user.model.User;
import com.wenku.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sandy on 29/06/2017.
 */
@Controller
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DocService docService;

    @Autowired
    private UserService userService;

    @Value("${server.doc}")
    private String docServer;

    @RequestMapping("view/{id}")
    public String view(@PathVariable long id, Model model){
        BaseDoc doc =  docService.findOne(DocTableNames.TABLE_PRODUCT, id);
        User author = userService.findOne(doc.getAuthorId());
        model.addAttribute("doc",doc);
        model.addAttribute("author",author);
        model.addAttribute("docServer",docServer);
        return "doc/view";
    }
}
