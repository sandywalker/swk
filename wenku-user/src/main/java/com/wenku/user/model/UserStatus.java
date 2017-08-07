package com.wenku.user.model;

/**
 * Created by sandy on 07/08/2017.
 */
public enum UserStatus {
    unactive("未激活"),active("激活");

    public String getTitle() {
        return title;
    }

    private String title;

    UserStatus(String title) {
        this.title = title;
    }
}
