package com.wenku.user.model;

/**
 * Created by sandy on 08/08/2017.
 */
public class UserACode {

    private Long uid;
    private String acode;

    public UserACode(Long uid, String acode) {
        this.uid = uid;
        this.acode = acode;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }




}
