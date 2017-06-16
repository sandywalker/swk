package com.wenku.doc.service;

import com.wenku.define.Result;
import com.wenku.doc.dao.DocDao;
import com.wenku.doc.model.*;
import com.wenku.support.data.PageUtils;
import com.wenku.util.MVCUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by sandy on 05/06/2017.
 */
@Service
@Transactional(readOnly = true,transactionManager = "tsDoc")
public class DocService {

    @Autowired
    private DocDao docDao;

    @Autowired
    private CheckLogService checkLogService;

    @Autowired
    private DocRecommendService docRecommendService;

    public Page<BaseDoc> findDocs(String table,Pageable pageable){
        PageUtils.startPage(pageable);
        List<BaseDoc> docs = docDao.find(table);
        return PageUtils.wrapPage(docs);
    }

    public BaseDoc findOne(String table,Long id){
        return docDao.findOne(table,id);
    }


    @Transactional("tsDoc")
    public BaseDoc save(String table,BaseDoc doc){
        if (doc.getId()>0){
            docDao.update(table,doc);
            return doc;
        }else{
            docDao.insert(table,doc);
            return doc;
        }
    }

    @Transactional("tsDoc")
    public BaseDoc save(String table,BaseDoc doc,long id,String... ignoreProperties){
        if (id>0){
            BaseDoc data = findOne(table,id);
            BeanUtils.copyProperties(doc,data,ignoreProperties);
            docDao.update(table,data);
            return data;
        }else{
            docDao.insert(table,doc);
            return doc;
        }
    }

    @Transactional("tsDoc")
    public BaseDoc save(String table,BaseDoc doc,long id){
        String[] ignores = MVCUtils.getIgnoredFields(BaseDoc.class);
        return save(table,doc,id,ignores);
    }
    
    @Transactional("tsDoc")
    public void delete(String table,Long id){
        BaseDoc baseDoc = findOne(table,id);
        docRecommendService.deleteByDid(baseDoc.getId());
        docDao.delete(table,id);
    }


    @Transactional("tsDoc")
    public Result approveDoc(Long id){
        BaseDoc data = docDao.findOne(DocTableNames.TABLE_UPLOAD,id);
        if (data==null){
            return Result.fail("数据不存在！");
        }
        data.setId(0L);
        docDao.insert(DocTableNames.TABLE_PRODUCT,data);
        docDao.delete(DocTableNames.TABLE_UPLOAD,id);
        CheckLog checkLog = new CheckLog(id,data.getAuthorId(), DocStatus.ok,"");
        checkLogService.insert(checkLog);
        return Result.success();
    }

    @Transactional("tsDoc")
    public Result rejectDoc(Long id){
        BaseDoc data = findOne(DocTableNames.TABLE_UPLOAD,id);
        if (data==null){
            return Result.fail("数据不存在！");
        }
        data.setId(0L);
        docDao.insert(DocTableNames.TABLE_TRASH,data);
        docDao.delete(DocTableNames.TABLE_UPLOAD,id);
        CheckLog checkLog = new CheckLog(id,data.getAuthorId(), DocStatus.failed,"审核不通过");
        checkLogService.insert(checkLog);
        return Result.success();
    }

    @Transactional("tsDoc")
    public Result addToRecommand(Long id){
        BaseDoc data = findOne(DocTableNames.TABLE_PRODUCT,id);
        if (data == null){
            return Result.fail("文档数据不存在！");
        }
        DocRecommend docRecommend = docRecommendService.findByDid(id);
        if (docRecommend==null){
            docRecommend = new DocRecommend();
            docRecommend.setDid(data.getId());
            docRecommend.setTitle(data.getTitle1());
            docRecommend.setCreateTime(new Date());
            docRecommendService.insert(docRecommend);
        }else{
            docRecommend.setTitle(data.getTitle1());
            docRecommendService.update(docRecommend);
        }
        return Result.success();
    }



}