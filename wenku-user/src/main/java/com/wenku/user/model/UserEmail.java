package com.wenku.user.model;

/**
 * Created by sandy on 07/08/2017.
 */
public class UserEmail {

    private Long uid;
    private String email;

    public UserEmail(Long uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String acode) {
        this.email = acode;
    }




}
