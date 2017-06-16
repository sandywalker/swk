package com.wenku.doc.web;

import com.wenku.define.Result;
import com.wenku.doc.model.DocRecommend;
import com.wenku.doc.model.DocRecommend;
import com.wenku.doc.model.DocTableNames;
import com.wenku.doc.model.DocUpload;
import com.wenku.doc.service.DocRecommendService;
import com.wenku.doc.service.DocService;
import com.wenku.util.MVCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by sandy on 14/06/2017.
 */
@Controller
@RequestMapping("/admin/doc")
public class AdminRecommendController {


    @Autowired
    private DocRecommendService docRecommendService;


    @RequestMapping("recommendHome")
    public String recommendHome(){
        return "admin/doc/recommends";
    }

    @RequestMapping("recommends")
    @ResponseBody
    public Page<DocRecommend> docUploads(Pageable pageable){
        return docRecommendService.findAll(pageable);
    }

    @GetMapping("recommends/{id}")
    @ResponseBody
    public DocRecommend getDocUpload(@PathVariable Long id){
        DocRecommend recommend = id==0?new DocRecommend():docRecommendService.findOne(id);
        return recommend;
    }

    @PostMapping("recommends/{id}")
    @ResponseBody
    public DocRecommend saveDocUpload(@PathVariable Long id, HttpServletRequest request,DocRecommend recommend, MultipartFile cover) throws IOException{
        String[] ignores = MVCUtils.getIgnoredFields(DocRecommend.class,request);
        DocRecommend docRecommend = docRecommendService.save(recommend,id,ignores);
            if (cover!=null&&!cover.isEmpty()){
            docRecommendService.saveCover(cover,docRecommend);
        }
        return docRecommend;
    }

    @DeleteMapping("recommends/{id}")
    @ResponseBody
    public Result deleteDocUpload(@PathVariable Long id){
        docRecommendService.delete(id);
        return Result.success();
    }

}
