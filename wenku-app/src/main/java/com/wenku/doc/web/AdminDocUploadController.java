package com.wenku.doc.web;

import com.wenku.define.Result;
import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.DocTableNames;
import com.wenku.doc.model.DocUpload;
import com.wenku.doc.service.DocService;
import com.wenku.doc.service.ProcQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sandy on 14/06/2017.
 */
@Controller
@RequestMapping("/admin/doc")
public class AdminDocUploadController {


    @Autowired
    private DocService docService;




    @RequestMapping("uploadHome")
    public String uploadHome(){
        return "admin/doc/uploads";
    }

    @RequestMapping("uploads")
    @ResponseBody
    public Page<BaseDoc> docUploads(Pageable pageable,String key){
        return docService.findUncheckedDoc(DocTableNames.TABLE_UPLOAD,key,pageable);
    }

    @GetMapping("uploads/{id}")
    @ResponseBody
    public BaseDoc getDocUpload(@PathVariable Long id){
        BaseDoc upload = id==0?new DocUpload():docService.findOne(DocTableNames.TABLE_UPLOAD,id);
        return upload;
    }

    @PostMapping("uploads/{id}")
    @ResponseBody
    public BaseDoc saveDocUpload(@PathVariable Long id, BaseDoc docUpload){
        return docService.save(DocTableNames.TABLE_UPLOAD,docUpload,id);
    }

    @DeleteMapping("uploads/{id}")
    @ResponseBody
    public Result deleteDocUpload(@PathVariable Long id){
        docService.delete(DocTableNames.TABLE_UPLOAD,id);
        return Result.success();
    }

    @RequestMapping("uploads/{id}/approve")
    @ResponseBody
    public Result approveDoc(@PathVariable Long id){
        return docService.approveDoc(id);
    }

    @RequestMapping("uploads/{id}/reject")
    @ResponseBody
    public Result rejectDoc(@PathVariable Long id){
        return docService.rejectDoc(id);
    }
}
