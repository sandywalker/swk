package com.wenku.doc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wenku.doc.model.TempUpload;

@Mapper
public interface TempUploadDao {


    TempUpload  findFirstByUidAndFilenameAndExt(@Param("uid")Long uid,@Param("filename")String filename,@Param("ext")String ext);



    TempUpload findById(@Param("id")Long id);

    int insert(@Param("pojo") TempUpload pojo);

    int insertSelective(@Param("pojo") TempUpload pojo);

    int insertList(@Param("pojos") List<TempUpload> pojo);

    int update(@Param("pojo") TempUpload pojo);

    int deleteById(@Param("id")Long id);


}
