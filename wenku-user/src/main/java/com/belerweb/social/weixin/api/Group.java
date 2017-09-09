package com.belerweb.social.weixin.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import com.belerweb.social.API;
import com.belerweb.social.bean.Error;
import com.belerweb.social.bean.Result;
import com.belerweb.social.exception.SocialException;

/**
 * 分组管理接口
 * 
 * 文档地址：http://mp.weixin.qq.com/wiki/index.php?title=分组管理接口
 */
public class Group extends API {

  protected Group(Weixin weixin) {
    super(weixin);
  }

  /**
   * 查询分组
   */
  public Result<List<com.belerweb.social.weixin.bean.Group>> get() {
    return get(weixin.getAccessToken().getToken());
  }

  /**
   * 查询分组
   * 
   * @param accessToken 调用接口凭证
   */
  public Result<List<com.belerweb.social.weixin.bean.Group>> get(String accessToken) {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    weixin.addParameter(params, "access_token", accessToken);
    String json = weixin.get("https://api.weixin.qq.com/cgi-bin/groups/get", params);
    JSONObject jsonObject = new JSONObject(json);
    Error error = Error.parse(jsonObject);
    if (error == null) {
      List<com.belerweb.social.weixin.bean.Group> groups =
          Result.parse(jsonObject.getJSONArray("groups"),
              com.belerweb.social.weixin.bean.Group.class);
      return new Result<List<com.belerweb.social.weixin.bean.Group>>(groups);
    }
    return new Result<List<com.belerweb.social.weixin.bean.Group>>(error);
  }

  /**
   * 创建分组
   * 
   * @param name 分组名字（30个字符以内）
   */
  public Result<com.belerweb.social.weixin.bean.Group> create(String name) {
    return create(weixin.getAccessToken().getToken(), name);
  }

  /**
   * 创建分组
   * 
   * 一个公众账号，最多支持创建500个分组。
   * 
   * @param accessToken 调用接口凭证
   * @param name 分组名字（30个字符以内）
   */
  public Result<com.belerweb.social.weixin.bean.Group> create(String accessToken, String name) {
    JSONObject request = new JSONObject();
    JSONObject group = new JSONObject();
    group.put("name", name);
    request.put("group", group);
    try {
      String json =
          weixin.post(
              "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=" + accessToken,
              new StringEntity(request.toString()));
      JSONObject jsonObject = new JSONObject(json);
      Error error = Error.parse(jsonObject);
      if (error != null) {
        return new Result<com.belerweb.social.weixin.bean.Group>(error);
      }
      return new Result<com.belerweb.social.weixin.bean.Group>(
          com.belerweb.social.weixin.bean.Group.parse(jsonObject.getJSONObject("group")));
    } catch (UnsupportedEncodingException e) {
      throw new SocialException(e);
    }
  }

  /**
   * 修改分组名
   * 
   * @param id 分组id，由微信分配
   * @param name 分组名字（30个字符以内）
   */
  public Result<Error> update(String id, String name) {
    return update(weixin.getAccessToken().getToken(), id, name);
  }

  /**
   * 修改分组名
   * 
   * @param accessToken 调用接口凭证
   * @param id 分组id，由微信分配
   * @param name 分组名字（30个字符以内）
   */
  public Result<Error> update(String accessToken, String id, String name) {
    JSONObject request = new JSONObject();
    JSONObject group = new JSONObject();
    group.put("id", id);
    group.put("name", name);
    request.put("group", group);
    try {
      String json =
          weixin.post(
              "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=" + accessToken,
              new StringEntity(request.toString()));
      return Result.parse(json, Error.class);
    } catch (UnsupportedEncodingException e) {
      throw new SocialException(e);
    }
  }

  /**
   * 移动用户分组
   * 
   * @param openId 用户唯一标识符
   * @param groupId 分组id
   */
  public Result<Error> move(String openId, String groupId) {
    return move(weixin.getAccessToken().getToken(), openId, groupId);
  }

  /**
   * 移动用户分组
   * 
   * @param accessToken 调用接口凭证
   * @param openId 用户唯一标识符
   * @param groupId 分组id
   */
  public Result<Error> move(String accessToken, String openId, String groupId) {
    JSONObject request = new JSONObject();
    request.put("openid", openId);
    request.put("to_groupid", groupId);
    try {
      String json =
          weixin.post("https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token="
              + accessToken, new StringEntity(request.toString()));
      return Result.parse(json, Error.class);
    } catch (UnsupportedEncodingException e) {
      throw new SocialException(e);
    }
  }

}
