package com.wenku.doc.service;

import com.wenku.doc.model.BaseDoc;
import com.wenku.queue.ProcQueue;
import com.wenku.support.data.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.wenku.doc.dao.ProcQueueDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,transactionManager = "tsDoc")
public class ProcQueueService{

    @Resource
    private ProcQueueDao procQueueDao;

    public Page<ProcQueue> find(Pageable pageable){
        PageUtils.startPage(pageable);
        List<ProcQueue> queues = procQueueDao.find();
        return PageUtils.wrapPage(queues);
    }

    public ProcQueue findOne(Long id){
        return procQueueDao.findById(id);
    }

    @Transactional("tsDoc")
    public int insert(ProcQueue pojo){
        return procQueueDao.insert(pojo);
    }

    @Transactional("tsDoc")
    public Long newQueue(Long uid,Long did,String fileType){
        ProcQueue procQueue = new ProcQueue(uid,did,fileType);
        insert(procQueue);
        return procQueue.getId();
    }

    @Transactional("tsDoc")
    public int insertSelective(ProcQueue pojo){
        return procQueueDao.insertSelective(pojo);
    }

    @Transactional("tsDoc")
    public int insertList(List<ProcQueue> pojos){
        return procQueueDao.insertList(pojos);
    }

    @Transactional("tsDoc")
    public int update(ProcQueue pojo){
        return procQueueDao.update(pojo);
    }

    @Transactional("tsDoc")
    public int delete(Long id){
        return procQueueDao.deleteById(id);
    }
}
