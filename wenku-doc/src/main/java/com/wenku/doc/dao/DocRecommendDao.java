package com.wenku.doc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wenku.doc.model.DocRecommend;

@Mapper
public interface DocRecommendDao {
    int insert(@Param("pojo") DocRecommend pojo);

    int insertSelective(@Param("pojo") DocRecommend pojo);

    int insertList(@Param("pojos") List<DocRecommend> pojo);

    int update(@Param("pojo") DocRecommend pojo);

    int deleteByDid(@Param("did")Long did);

    DocRecommend findFirstByDid(@Param("did")Long did);

    List<DocRecommend> find();

    DocRecommend findById(@Param("id")Long id);

    int deleteById(@Param("id")Long id);





}
