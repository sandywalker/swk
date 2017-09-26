package com.wenku.queue;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenku.util.DateTimeFormats;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by sandy on 09/09/2017.
 */
public class ProcQueue {

    private Long id;
    private Long uid;
    private Long did;
    private ProcState state;
    private String msg;
    private String fileType;
    private Date createTime;
    private Date updateTime;

    public ProcQueue() {
    }

    public ProcQueue(Long uid, Long did,String fileType) {
        this.uid = uid;
        this.did = did;
        this.fileType = fileType;
        this.state = ProcState.wait;
        this.createTime = new Date();
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

    public ProcState getState() {
        return state;
    }

    public void setState(ProcState state) {
        this.state = state;
    }

    @DateTimeFormat(pattern = DateTimeFormats.DEFAULT_DATETIME_FORMAT)
    @JsonFormat(pattern = DateTimeFormats.DEFAULT_DATETIME_FORMAT)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @DateTimeFormat(pattern = DateTimeFormats.DEFAULT_DATETIME_FORMAT)
    @JsonFormat(pattern = DateTimeFormats.DEFAULT_DATETIME_FORMAT)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}

