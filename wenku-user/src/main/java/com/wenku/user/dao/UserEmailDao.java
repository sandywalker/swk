package com.wenku.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wenku.user.model.UserEmail;

@Mapper
public interface UserEmailDao {
    int insert(@Param("pojo") UserEmail pojo);

    int insertSelective(@Param("pojo") UserEmail pojo);

    int insertList(@Param("pojos") List<UserEmail> pojo);

    int update(@Param("pojo") UserEmail pojo);

    UserEmail findFirstByEmail(@Param("email")String email);




}
