package com.wenku.doc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenku.util.DateTimeFormats;
import org.springframework.format.annotation.DateTimeFormat;

import javax.print.Doc;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sandy on 05/06/2017.
 */
public class BaseDoc implements Serializable{

    private Long id;
    private String title1;
    private String title2;
    private String keywords;
    private String description;
    private String seoKeywords;
    private String abstracts;
    private DocStatus status = DocStatus.unchecked;
    private DocSrc src = DocSrc.user;
    private Integer levels = 0;
    private BigDecimal price = BigDecimal.ZERO;
    private Long authorId;
    private Date createTime;

    private String statusStr;
    private String srcStr;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public DocStatus getStatus() {
        return status;
    }

    public void setStatus(DocStatus status) {
        this.status = status;
    }

    public DocSrc getSrc() {
        return src;
    }

    public void setSrc(DocSrc src) {
        this.src = src;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @DateTimeFormat(pattern = DateTimeFormats.DEFAULT_DATETIME_FORMAT)
    @JsonFormat(pattern = DateTimeFormats.DEFAULT_DATETIME_FORMAT)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatusStr() {
        return status!=null?status.getTitle():"";
    }

    public String getSrcStr() {
        return src!=null?src.getTitle():"";
    }

}
