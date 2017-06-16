package com.wenku.doc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wenku.doc.model.CheckLog;

@Mapper
public interface CheckLogDao {
    int insert(@Param("pojo") CheckLog pojo);

    int insertSelective(@Param("pojo") CheckLog pojo);

    int insertList(@Param("pojos") List<CheckLog> pojo);

    int update(@Param("pojo") CheckLog pojo);

    List<CheckLog> find();


}
