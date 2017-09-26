package com.wenku.doc.model;

import java.util.Date;

/**
 * Created by sandy on 10/09/2017.
 */
public class TempUpload {

    private Long id;
    private Long uid;
    private String filename;
    private String ext;
    private Date createTime;

    public TempUpload(Long uid, String filename, String ext) {
        this.uid = uid;
        this.filename = filename;
        this.ext = ext;
        this.createTime = new Date();
    }

    public TempUpload() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
