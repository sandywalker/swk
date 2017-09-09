package com.wenku.user.model;

import com.wenku.util.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Created by sandy on 07/08/2017.
 */
public class User {

    private Long id;
    private String phone;
    private String email;
    private String pass;
    private String salt;
    private String nickName;
    private UserStatus status = UserStatus.unactive;
    private UserType utype;
    private Date createDate;
    private Date updateDate;
    private String loginName; //计算字段

    public static User newUserByEmail(String mail,String pass){
        User user = new User();
        user.setEmail(mail);
        try {
            user.setPass(DigestUtils.md5(pass,user.getSalt().getBytes()));
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setUtype(UserType.email);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSalt() {
        if (StringUtils.isNotBlank(email)&&email.length()>3) {
            salt = email.substring(0, 2) + email.substring(email.length() - 2);
        }
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserType getUtype() {
        return utype;
    }

    public void setUtype(UserType utype) {
        this.utype = utype;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getLoginName() {
        if (StringUtils.isNotBlank(getPhone())){
            return getPhone();
        }
        return getEmail();
    }

    public String getNickName() {
        if (StringUtils.isBlank(nickName)){
            return getLoginName();
        }
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static void main(String[] args) {
        System.out.println("abc".substring(0,2));
    }




}
