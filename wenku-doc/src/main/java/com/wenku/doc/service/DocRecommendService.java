package com.wenku.doc.service;

import com.wenku.doc.model.BaseDoc;
import com.wenku.doc.model.DocRecommend;
import com.wenku.support.data.PageUtils;
import com.wenku.util.MVCUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.print.Doc;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.wenku.doc.dao.DocRecommendDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true,transactionManager = "tsDoc")
public class DocRecommendService{

    @Resource
    private DocRecommendDao docRecommendDao;

    @Transactional("tsDoc")
    public int insert(DocRecommend pojo){
        return docRecommendDao.insert(pojo);
    }

    @Transactional("tsDoc")
    public int insertSelective(DocRecommend pojo){
        return docRecommendDao.insertSelective(pojo);
    }

    @Transactional("tsDoc")
    public int insertList(List<DocRecommend> pojos){
        return docRecommendDao.insertList(pojos);
    }

    @Transactional("tsDoc")
    public int deleteByDid(Long did){
        return docRecommendDao.deleteByDid(did);
    }

    public DocRecommend findOne(Long id){
        return docRecommendDao.findById(id);
    }


    @Transactional("tsDoc")
    public DocRecommend save(DocRecommend recommend, long id,String[] ignores){
        if (id>0){
            DocRecommend data = docRecommendDao.findById(id);
            BeanUtils.copyProperties(recommend,data,ignores);
            docRecommendDao.update(data);
            return data;
        }else{
            docRecommendDao.insert(recommend);
            return recommend;
        }
    }

    @Transactional("tsDoc")
    public DocRecommend save(DocRecommend recommend, long id){
        String[] ignores = MVCUtils.getIgnoredFields(DocRecommend.class);
        return save(recommend,id,ignores);
    }

    private String getCoverPath(DocRecommend recommend){
        return MVCUtils.getWebRoot() + "/resources/img/recommend/" + recommend.getId() + ".jpg";
    }

    public void saveCover(MultipartFile cover, DocRecommend recommend) throws IOException{
        //TODO 以后要改成往文件服务器处理
        String filepath = getCoverPath(recommend);
        FileCopyUtils.copy(cover.getInputStream(),new FileOutputStream(filepath));
    }

    @Transactional("tsDoc")
    public void delete(Long id){
        DocRecommend recommend = findOne(id);
        String filepath = getCoverPath(recommend);
        docRecommendDao.deleteById(id);
        //TODO 以后要改成往文件服务器处理
        File cover = new File(filepath);
        if (cover.exists()){
            try {
                FileUtils.forceDelete(cover);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional("tsDoc")
    public int update(DocRecommend pojo){
        return docRecommendDao.update(pojo);
    }

    public Page<DocRecommend> findAll(Pageable pageable){
        PageUtils.startPage(pageable);
        List<DocRecommend> list = docRecommendDao.find();
        return PageUtils.wrapPage(list);
    }

    public DocRecommend findByDid(Long did){
       return docRecommendDao.findFirstByDid(did);
    }
}
