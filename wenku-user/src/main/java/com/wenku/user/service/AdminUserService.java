package com.wenku.user.service;

import com.wenku.user.dao.AdminUserDao;
import com.wenku.user.model.AdminAuth;
import com.wenku.user.model.AdminUser;
import com.wenku.util.DigestUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true,value = "tsUser")
public class AdminUserService{

    @Resource
    private AdminUserDao adminUserDao;

    @Transactional("tsUser")
    public int insert(AdminUser pojo){
        return adminUserDao.insert(pojo);
    }

    @Transactional("tsUser")
    public int insertSelective(AdminUser pojo){
        return adminUserDao.insertSelective(pojo);
    }

    @Transactional("tsUser")
    public int insertList(List<AdminUser> pojos){
        return adminUserDao.insertList(pojos);
    }

    @Transactional("tsUser")
    public int update(AdminUser pojo){
        return adminUserDao.update(pojo);
    }

    public AdminUser findByUsername(String username){
        return adminUserDao.findFirstByUsername(username);
    }

    @Transactional("tsUser")
    public int createAdmin(AdminUser adminUser){
        Assert.hasText(adminUser.getPassword(),"密码不能为空！");
        String password = adminUser.getPassword();
        String username = adminUser.getUsername();
        password = AdminAuth.encryPassword(username,password);
        adminUser.setPassword(password);
        return adminUserDao.insert(adminUser);
    }
}
