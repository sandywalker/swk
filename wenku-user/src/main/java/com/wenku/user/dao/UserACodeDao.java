package com.wenku.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wenku.user.model.UserACode;

@Mapper
public interface UserACodeDao {

    UserACode findFirstByUid(@Param("uid")Long uid);



    int insert(@Param("pojo") UserACode pojo);

    int insertSelective(@Param("pojo") UserACode pojo);

    int insertList(@Param("pojos") List<UserACode> pojo);

    int update(@Param("pojo") UserACode pojo);

    int deleteByUid(@Param("uid")Long uid);


}
