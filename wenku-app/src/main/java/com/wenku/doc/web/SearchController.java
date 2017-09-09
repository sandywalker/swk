package com.wenku.doc.web;

import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.DocTableNames;
import com.wenku.doc.service.DocService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by sandy on 27/06/2017.
 */
@Controller
public class SearchController {

    @Resource
    private DocService docService;

    @RequestMapping("search")
    public String search(String key, Pageable pageable, Model model){
        Page<BaseDoc> page = docService.findDocs(DocTableNames.TABLE_PRODUCT,key,pageable);
        model.addAttribute("key",key);
        model.addAttribute("page",page);
        model.addAttribute("docs",page.getContent());
        model.addAttribute("total",page.getTotalElements());
        return "search";
    }
}
