package com.wenku.doc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenku.util.DateTimeFormats;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sandy on 14/06/2017.
 */
public class CheckLog implements Serializable{

    private Long id;
    private Long did;
    private Long uid;
    private Long cid;
    private DocStatus result;
    private String message;
    private Date createTime;

    public CheckLog(){
        super();
    }

    public CheckLog(Long did,Long uid, DocStatus result, String message) {
        this.did = did;
        this.result = result;
        this.message = message;
        this.createTime = new Date();
        this.uid = uid;
        this.cid = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public DocStatus getResult() {
        return result;
    }

    public void setResult(DocStatus result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @DateTimeFormat(pattern = DateTimeFormats.DEFAULT_DATETIME_FORMAT)
    @JsonFormat(pattern = DateTimeFormats.DEFAULT_DATETIME_FORMAT)
    public Date getCreateTime() {
        return createTime;
    }


    public String getResultStr() {
        return result!=null?result.getTitle():"";
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }


}
