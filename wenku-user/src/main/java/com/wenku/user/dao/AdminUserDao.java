package com.wenku.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.wenku.user.model.AdminUser;

@Mapper
public interface AdminUserDao {
    int insert(@Param("pojo") AdminUser pojo);

    int insertSelective(@Param("pojo") AdminUser pojo);

    int insertList(@Param("pojos") List<AdminUser> pojo);

    int update(@Param("pojo") AdminUser pojo);

    AdminUser findFirstByUsername(@Param("username")String username);


}
