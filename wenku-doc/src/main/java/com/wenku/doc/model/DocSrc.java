package com.wenku.doc.model;

/**
 * Created by sandy on 12/06/2017.
 */
public enum DocSrc {
    user("用户"),admin("后台管理员"),system("系统");

    private String title;

    DocSrc(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
