package com.wenku.doc.web;

import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.CheckLog;
import com.wenku.doc.model.DocTableNames;
import com.wenku.doc.service.CheckLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sandy on 14/06/2017.
 */
@Controller
@RequestMapping("/admin/doc")
public class AdminCheckLogController {

    @Autowired
    private CheckLogService checkLogService;

    @RequestMapping("checkLogHome")
    public String checkLogHome(){
        return "admin/doc/checklogs";
    }

    @RequestMapping("checkLogs")
    @ResponseBody
    public Page<CheckLog> checkLogs(Pageable pageable){
        return checkLogService.findAll(pageable);
    }


}
