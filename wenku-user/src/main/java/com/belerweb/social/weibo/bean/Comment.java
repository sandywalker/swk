package com.belerweb.social.weibo.bean;

import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import com.belerweb.social.bean.JsonBean;
import com.belerweb.social.bean.Result;

/**
 * 评论
 * 
 * 文档地址：http://open.weibo.com/wiki/常见返回对象数据结构#.E8.AF.84.E8.AE.BA.EF.BC.88comment.EF.BC.89
 */
public class Comment extends JsonBean {

  public Comment() {}

  private Comment(JSONObject jsonObject) {
    super(jsonObject);
  }

  private String id;// 评论的ID
  private String mid;// 评论的MID
  private String idstr;// 字符串型的评论ID
  private Date createdAt;// 评论创建时间
  private String text;// 评论的内容
  private String source;// 评论的来源
  private User user;// 评论作者的用户信息字段
  private Status status;// 评论的微博信息字段
  private Comment replyComment;// 评论来源评论，当本评论属于对另一评论的回复时返回此字段

  /**
   * 评论的ID
   */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * 评论的MID
   */
  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

  /**
   * 字符串型的评论ID
   */
  public String getIdstr() {
    return idstr;
  }

  public void setIdstr(String idstr) {
    this.idstr = idstr;
  }

  /**
   * 评论创建时间
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * 评论的内容
   */
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  /**
   * 评论的来源
   */
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  /**
   * 评论作者的用户信息字段
   */
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  /**
   * 评论的微博信息字段
   */
  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * 评论来源评论，当本评论属于对另一评论的回复时返回此字段
   */
  public Comment getReplyComment() {
    return replyComment;
  }

  public void setReplyComment(Comment replyComment) {
    this.replyComment = replyComment;
  }

  public static Comment parse(JSONObject jsonObject) {
    if (jsonObject == null) {
      return null;
    }
    Comment obj = new Comment(jsonObject);
    obj.id = Result.toString(jsonObject.get("id"));
    obj.mid = Result.toString(jsonObject.opt("mid"));
    obj.idstr = Result.toString(jsonObject.opt("idstr"));
    obj.createdAt =
        Result
            .parseDate(jsonObject.opt("created_at"), "EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);

    obj.text = Result.toString(jsonObject.get("text"));
    obj.source = Result.toString(jsonObject.opt("source"));

    obj.user = User.parse(jsonObject.optJSONObject("user"));
    obj.status = Status.parse(jsonObject);
    obj.replyComment = Comment.parse(jsonObject.optJSONObject("reply_comment"));
    return obj;
  }

}
