package com.wenku.doc.model;

/**
 * Created by sandy on 05/06/2017.
 */
public enum  DocStatus {

    unchecked("待审核"),ok("通过"),failed("未通过");

    private String title;

    public String getTitle() {
        return title;
    }

    DocStatus(String title) {
        this.title = title;
    }
}
