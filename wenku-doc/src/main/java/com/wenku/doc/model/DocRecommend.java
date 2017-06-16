package com.wenku.doc.model;

import java.util.Date;

/**
 * Created by sandy on 14/06/2017.
 */
public class DocRecommend {

    private Long id;
    private Long did;
    private String title;
    private String url;
    private Integer orderNumber = 99;
    private Date createTime;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
