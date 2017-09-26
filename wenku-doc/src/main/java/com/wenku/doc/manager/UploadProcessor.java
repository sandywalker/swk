package com.wenku.doc.manager;

import com.wenku.define.Result;
import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.TempUpload;
import com.wenku.doc.service.DocService;
import com.wenku.doc.service.ProcQueueService;
import com.wenku.doc.service.TempUploadService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sandy on 09/09/2017.
 */
@Component
public class UploadProcessor {

    private static final Logger logger = LoggerFactory.getLogger(UploadProcessor.class);

    @Value("${dir.doc.temp}")
    private String tempRoot;

    @Value("${dir.doc.file}")
    private String fileRoot;

    @Value("${server.convert}")
    private String convertServer;


    @Resource
    private ProcQueueService procQueueService;

    @Resource
    private TempUploadService tempUploadService;

    @Resource
    private DocService docService;

    /**
     * 通知转换服务器转换
     * @return
     */
    public Result notifyConvertServer(){
        RestTemplate restTemplate = new RestTemplate();
        Result result;
        try {
            result = restTemplate.getForObject(convertServer + "/api/convert/notify", Result.class);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return Result.fail(e.getMessage());
        }
        logger.info("notify send with result: " + result.isSuccess()+ ", message: " + result.getMessage());
        return result;
    }

    public boolean tempUpload(Long tempId,Long uid, MultipartFile file) throws IOException{
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        File tempFile = new File(tempRoot + File.separator + tempId + "." + ext);
        try {
            IOUtils.copy(file.getInputStream(), new FileOutputStream(tempFile));
        }catch (Exception e){
            e.printStackTrace();
            tempUploadService.deleteById(tempId);
            return false;
        }
        return true;
    }

    public boolean removeTempUpload(Long tempId){

        TempUpload tempUpload = tempUploadService.findOne(tempId);
        if (tempUpload == null){
            return false;
        }

        File tempFile = new File(tempRoot + File.separator + tempId + "." + tempUpload.getExt());
        try {
            if (tempFile.exists()) {
                FileUtils.forceDelete(tempFile);
                tempUploadService.deleteById(tempId);
            }
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean completeUpload(Long tempId, BaseDoc baseDoc, Long uid){
        String ext = baseDoc.getFileType();
        File tempFile = new File(tempRoot + File.separator + tempId + "." + ext);
        File file = new File(fileRoot + File.separator + baseDoc.getId() + "." + ext);
        try {
            FileUtils.copyFile(tempFile,file);
            procQueueService.newQueue(uid,baseDoc.getId(),ext);
            tempUploadService.deleteById(tempId);
            FileUtils.deleteQuietly(tempFile);
            notifyConvertServer();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(  "http://10.211.55.5:8080/convert/api/convert/hello", String.class);
        System.out.println(result);

    }



}
