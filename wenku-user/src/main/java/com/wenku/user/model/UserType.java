package com.wenku.user.model;

/**
 * Created by sandy on 07/08/2017.
 */
public enum  UserType {

    email("电子邮件"),phone("手机"),weibo("微博"),weixin("微信"),qq("QQ");

    private String title;

    public String getTitle() {
        return title;
    }

    UserType(String title) {
        this.title = title;
    }


}
