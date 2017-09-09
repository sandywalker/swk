package com.belerweb.social.weibo.bean;

import org.json.JSONObject;

import com.belerweb.social.bean.JsonBean;
import com.belerweb.social.bean.Result;

/**
 * 短链
 */
public class UrlShort extends JsonBean {

  public UrlShort() {}

  private UrlShort(JSONObject jsonObject) {
    super(jsonObject);
  }

  private String urlShort;// 短链接
  private String urlLong;// 原始长链接
  private Integer type;// 链接的类型，0：普通网页、1：视频、2：音乐、3：活动、5、投票
  private Boolean result;// 短链的可用状态，true：可用、false：不可用。

  /**
   * 短链接
   */
  public String getUrlShort() {
    return urlShort;
  }

  public void setUrlShort(String urlShort) {
    this.urlShort = urlShort;
  }

  /**
   * 原始长链接
   */
  public String getUrlLong() {
    return urlLong;
  }

  public void setUrlLong(String urlLong) {
    this.urlLong = urlLong;
  }

  /**
   * 链接的类型，0：普通网页、1：视频、2：音乐、3：活动、5、投票
   */
  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  /**
   * 短链的可用状态，true：可用、false：不可用。
   */
  public Boolean getResult() {
    return result;
  }

  public void setResult(Boolean result) {
    this.result = result;
  }

  public static UrlShort parse(JSONObject jsonObject) {
    if (jsonObject == null) {
      return null;
    }
    UrlShort obj = new UrlShort(jsonObject);
    obj.urlShort = Result.toString(jsonObject.opt("url_short"));
    obj.urlLong = Result.toString(jsonObject.opt("url_long"));
    obj.type = Result.parseInteger(jsonObject.opt("type"));
    obj.result = Result.parseBoolean(jsonObject.opt("result"));
    return obj;
  }
}
