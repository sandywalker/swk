package com.wenku.api;

import com.wenku.convert.ConvertDao;
import com.wenku.convert.ConvertManager;
import com.wenku.define.Result;
import com.wenku.queue.ProcQueue;
import com.wenku.queue.ProcState;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sandy on 11/09/2017.
 */
@RestController
@RequestMapping("/api/convert")
public class ConvertApi {

    @Resource
    private ConvertDao procQueueDS;

    @Resource
    private ConvertManager convertManager;

    @RequestMapping("hello")
    public String hello(){
        return " world";
    }

    @RequestMapping("/notify")
    public Result fetchProcQueue(){
        List<ProcQueue> queueList = procQueueDS.findQueueByState(ProcState.wait);
        if (CollectionUtils.isNotEmpty(queueList)) {
            convertManager.convert(queueList);
            return Result.success("已获取到 " + queueList.size() + " 条转换请求！");
        }else{
            return Result.fail("没有新任务！");
        }
    }



}
