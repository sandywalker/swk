package com.wenku.doc.service;

import com.wenku.doc.model.TempUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.wenku.doc.dao.TempUploadDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true,transactionManager = "tsDoc")
public class TempUploadService{

    @Resource
    private TempUploadDao tempUploadDao;

    public TempUpload findOne(Long id){
        return tempUploadDao.findById(id);
    }

    public TempUpload findExists(Long uid,String title,String fileType){
        return tempUploadDao.findFirstByUidAndFilenameAndExt(uid,title,fileType);
    }

    @Transactional("tsDoc")
    public TempUpload newUpload(Long uid,String title,String fileType){
        TempUpload tempUpload = tempUploadDao.findFirstByUidAndFilenameAndExt(uid,title,fileType);
        if (tempUpload==null){
            tempUpload = new TempUpload(uid,title,fileType);
            tempUploadDao.insert(tempUpload);
        }
        return tempUpload;
    }

    @Transactional("tsDoc")
    public int insert(TempUpload pojo){
        return tempUploadDao.insert(pojo);
    }

    @Transactional("tsDoc")
    public int insertSelective(TempUpload pojo){
        return tempUploadDao.insertSelective(pojo);
    }

    @Transactional("tsDoc")
    public int insertList(List<TempUpload> pojos){
        return tempUploadDao.insertList(pojos);
    }

    @Transactional("tsDoc")
    public int update(TempUpload pojo){
        return tempUploadDao.update(pojo);
    }

    @Transactional("tsDoc")
    public int deleteById(Long id){
        return tempUploadDao.deleteById(id);
    };


}
