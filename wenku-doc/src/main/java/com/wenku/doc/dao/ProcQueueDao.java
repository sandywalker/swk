package com.wenku.doc.dao;

import com.wenku.queue.ProcQueue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProcQueueDao {
    int insert(@Param("pojo") ProcQueue pojo);

    int insertSelective(@Param("pojo") ProcQueue pojo);

    int insertList(@Param("pojos") List<ProcQueue> pojo);

    int update(@Param("pojo") ProcQueue pojo);

    int deleteById(@Param("id")Long id);

    List<ProcQueue> find();

    ProcQueue findById(@Param("id")Long id);

}
