package com.wenku.doc.web;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.wenku.define.Result;
import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.DocProduct;
import com.wenku.doc.model.DocTableNames;
import com.wenku.doc.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sandy on 08/06/2017.
 */
@Controller
@RequestMapping("/admin/doc")
public class AdminDocProductController {

    @Autowired
    private DocService docService;

    @RequestMapping("productHome")
    public String productHome(){
        return "admin/doc/products";
    }

    @RequestMapping("products")
    @ResponseBody
    public Page<BaseDoc> docProducts(Pageable pageable){
        return docService.findDocs(DocTableNames.TABLE_PRODUCT,pageable);
    }

    @GetMapping(value = "products/{id}")
    @ResponseBody
    public BaseDoc getDocProduct(@PathVariable Long id){
        BaseDoc product = id==0?new DocProduct():docService.findOne(DocTableNames.TABLE_PRODUCT,id);
        return product;
    }

    @PostMapping(value = "products/{id}")
    @ResponseBody
    public BaseDoc saveDocProduct(@PathVariable Long id, BaseDoc docProduct){
        return docService.save(DocTableNames.TABLE_PRODUCT,docProduct,id);
    }

    @DeleteMapping(value = "products/{id}")
    @ResponseBody
    public Result deleteDocProduct(@PathVariable Long id){
        docService.delete(DocTableNames.TABLE_PRODUCT,id);
        return Result.success();
    }

    @RequestMapping("products/{id}/recommend")
    @ResponseBody
    public Result recommendDocProduct(@PathVariable Long id){
        return docService.addToRecommand(id);
    }
}
