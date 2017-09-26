package com.wenku.convert;

import com.wenku.define.Result;
import com.wenku.queue.ProcQueue;
import com.wenku.queue.ProcState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by sandy on 11/09/2017.
 */
@Component
public class ConvertManager {

    @Value("${dir.doc.file}")
    private String fileRoot;

    @Value("${dir.doc.html}")
    private String htmlRoot;

    @Value("${dir.doc.pdf}")
    private String pdfRoot;

    @Value("${dir.doc.font}")
    private String fontPath;

    private ExecutorService executor;

    @Resource
    private ConvertDao convertDao;

    @PostConstruct
    public void init(){
        int pollSize = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(pollSize);
    }

    public void convert(List<ProcQueue> queueList){
        for(ProcQueue procQueue:queueList){
            convert(procQueue);
        }
    }

    private void convert(ProcQueue procQueue){
        String filePath = fileRoot + File.separator + procQueue.getDid() + "." + procQueue.getFileType();
        File file = new File(filePath);
        if(!file.exists()){
            convertDao.updateQueueState(procQueue.getId(), ProcState.failed,"file: " + filePath + " not exists!");
            return;
        }
        String pdfPath = pdfRoot + File.separator + procQueue.getDid() + ".pdf";
        String htmlPath = htmlRoot + File.separator + procQueue.getDid();
        convertDao.updateQueueState(procQueue.getId(), ProcState.processing,null);
        ConvertTask convertTask = new ConvertTask(convertDao,procQueue.getId(), procQueue.getDid(), filePath,pdfPath,htmlPath,fontPath);
        Future<Result> future = executor.submit(convertTask);
        //future.get()

    }



}
