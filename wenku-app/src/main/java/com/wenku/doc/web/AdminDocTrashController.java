package com.wenku.doc.web;

import com.wenku.define.Result;
import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.DocTableNames;
import com.wenku.doc.model.DocTrash;
import com.wenku.doc.service.DocService;
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
public class AdminDocTrashController {


    @Autowired
    private DocService docService;


    @RequestMapping("trashHome")
    public String trashHome(){
        return "admin/doc/trashs";
    }

    @RequestMapping("trashs")
    @ResponseBody
    public Page<BaseDoc> docUploads(Pageable pageable){
        return docService.findDocs(DocTableNames.TABLE_TRASH,pageable);
    }

    @GetMapping(value = "trashs/{id}")
    @ResponseBody
    public BaseDoc getDocTrash(@PathVariable Long id){
        BaseDoc trash = id==0?new DocTrash():docService.findOne(DocTableNames.TABLE_TRASH,id);
        return trash;
    }

    @PostMapping(value = "trashs/{id}")
    @ResponseBody
    public BaseDoc saveDocTrash(@PathVariable Long id, BaseDoc docTrash){
        return docService.save(DocTableNames.TABLE_TRASH,docTrash,id);
    }

    @DeleteMapping(value = "trashs/{id}")
    @ResponseBody
    public Result deleteDocTrash(@PathVariable Long id){
        docService.delete(DocTableNames.TABLE_TRASH,id);
        return Result.success();
    }
}
