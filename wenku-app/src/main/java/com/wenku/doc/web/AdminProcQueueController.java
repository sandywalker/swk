package com.wenku.doc.web;

import com.wenku.define.Result;
import com.wenku.doc.manager.UploadProcessor;
import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.DocTableNames;
import com.wenku.doc.service.ProcQueueService;
import com.wenku.queue.ProcQueue;
import com.wenku.queue.ProcState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sandy on 24/09/2017.
 */
@Controller
@RequestMapping("/admin/doc")
public class AdminProcQueueController {

    @Autowired
    private ProcQueueService procQueueService;

    @Autowired
    private  UploadProcessor uploadProcessor;

    @RequestMapping("procQueue")
    public String procQueueHome(){
        return "admin/doc/procQueue";
    }

    @RequestMapping("proc/queues")
    @ResponseBody
    public Page<ProcQueue> queue(Pageable pageable){
        return procQueueService.find(pageable);
    }

    @RequestMapping("proc/reproc")
    @ResponseBody
    public Result reproc(Long[] ids){
        for(Long id:ids){
            ProcQueue queue = procQueueService.findOne(id);
            if (queue!=null){
                queue.setState(ProcState.wait);
                procQueueService.update(queue);
            }
        }
        return uploadProcessor.notifyConvertServer();
    }


}
