package com.wenku.user.web;

import com.wenku.define.Result;
import com.wenku.doc.manager.UploadProcessor;
import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.DocStatus;
import com.wenku.doc.model.DocTableNames;
import com.wenku.doc.model.TempUpload;
import com.wenku.doc.service.DocService;
import com.wenku.doc.service.TempUploadService;
import com.wenku.security.Guard;
import com.wenku.user.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sandy on 09/09/2017.
 */
@Controller
@RequestMapping("/user/doc")
public class UserDocController {

    @Resource
    private UserService userService;

    @Resource
    private Guard guard;

    @Resource
    private DocService docService;

    @Resource
    private TempUploadService tempUploadService;

    @Resource
    private UploadProcessor uploadProcessor;


    @GetMapping("upload")
    public String upload(){
        return "user/upload";
    }

    /**
     * status   wait,ok
     * @param model
     * @return
     */
    @GetMapping("myUpload/{status}")
    public String myUpload(@PathVariable String status, Model model, HttpServletRequest request){
        DocStatus docStatus =  DocStatus.valueOf(status) ;
        List<BaseDoc> docs =  docService.findByAuthorAndStatus(Guard.getUID(request),docStatus);
        model.addAttribute("docs",docs);
        model.addAttribute("status",status);
        return "user/my-upload";
    }

    @RequestMapping("doUpload")
    @ResponseBody
    public Result doUpload(HttpServletRequest request, MultipartFile file){
        try{
            String title = FilenameUtils.getBaseName(file.getOriginalFilename());
            if (title.length()>64){
                title = title.substring(0,63);
            }
            System.out.println(title.length());
            String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
            Long uid = Guard.getUID(request);
            TempUpload tempUpload = tempUploadService.findExists(uid,title,fileType);
            if (tempUpload == null){
                tempUpload = tempUploadService.newUpload(uid,title,fileType);
                uploadProcessor.tempUpload(tempUpload.getId(),uid,file);
            }
            return Result.success("上传成功",tempUpload);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("upload/removeTemp/{id}")
    @ResponseBody
    public Result removeTemp( Long id,HttpServletRequest req){
       boolean succ =  uploadProcessor.removeTempUpload(id);
       return succ?Result.success():Result.fail("");
    }

    @RequestMapping("upload/complete")
    @ResponseBody
    public Result completeUpload( Long id,String fileType,String title,String description,HttpServletRequest req){
        try{
            Long uid = Guard.getUID(req);
            BaseDoc baseDoc =  docService.newUpload(title,fileType,description,uid);
            uploadProcessor.completeUpload(id,baseDoc,uid);
            return Result.success("成功",baseDoc);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail("失败",id);
        }
    }

}
