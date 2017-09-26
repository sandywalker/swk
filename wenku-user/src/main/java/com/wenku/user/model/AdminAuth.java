package com.wenku.user.model;

import com.wenku.define.Result;
import com.wenku.user.service.AdminUserService;
import com.wenku.util.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by sandy on 16/06/2017.
 */
@Component
public class AdminAuth {

    @Autowired
    private AdminUserService adminUserService;

    public static final  String ADMIN_SESSION_ATTR = "wenku.user.admin.username";

    /**
     * 加密明文密码
     * @param username 用户名(用作盐值)
     * @param password 密码
     * @return
     */
    public static String encryPassword(String username,String password){
        String encryptPass = "";
        try {
            encryptPass = DigestUtils.md5(password,username.substring(1).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encryptPass;
    }


    /**
     * 登录验证
     * @param username 用户名
     * @param password 明文密码
     * @return
     */
    public Result auth(String username,String password){
        if (StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return Result.fail("用户或密码不能为空！");
        }
        AdminUser admin = adminUserService.findByUsername(username);
        if (admin==null){
            return Result.fail("用户名不存在！");
        }
        String ecPass = encryPassword(username,password);
        if (!ecPass.equals(admin.getPassword())){
            return Result.fail("密码错误！");
        }
        if (admin.getLocked()){
            return Result.fail("用户已被锁定！");
        }
        return Result.success();
    }

    public static void main(String[] args) throws IOException{
        System.out.println(DigestUtils.md5("admin@wk123","admin".substring(1).getBytes()));
    }
}
