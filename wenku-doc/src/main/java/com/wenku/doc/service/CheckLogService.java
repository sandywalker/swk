package com.wenku.doc.service;

import com.wenku.doc.model.CheckLog;
import com.wenku.support.data.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.wenku.doc.dao.CheckLogDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,transactionManager = "tsDoc")
public class CheckLogService{

    @Resource
    private CheckLogDao checkLogDao;

    @Transactional("tsDoc")
    public int insert(CheckLog pojo){
        //TODO 设置审核人 ID
        return checkLogDao.insert(pojo);
    }

    @Transactional("tsDoc")
    public int insertSelective(CheckLog pojo){
        return checkLogDao.insertSelective(pojo);
    }

    @Transactional("tsDoc")
    public int insertList(List<CheckLog> pojos){
        return checkLogDao.insertList(pojos);
    }

    @Transactional("tsDoc")
    public int update(CheckLog pojo){
        return checkLogDao.update(pojo);
    }

    public Page<CheckLog> findAll(Pageable pageable){
        PageUtils.startPage(pageable);
        List<CheckLog> list = checkLogDao.find();
        return PageUtils.wrapPage(list);
    }
}
