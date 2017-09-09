package com.belerweb.social;

import com.wenku.web.interceptor.SystemVariableInterceptor;
import org.junit.Before;

import com.belerweb.social.qq.connect.api.QQConnect;
import com.belerweb.social.weibo.api.Weibo;
import com.belerweb.social.weixin.api.Weixin;

public class TestConfig {

  protected Weibo weibo;
  protected QQConnect connect;
  protected Weixin weixin;

  @Before
  public void initialize() {
    System.setProperty("weibo.id","2001117563");
    System.setProperty("weibo.secret","9a940f72680c3a984e587c01b0d66e24");
    System.setProperty("redirect","http://www.sowenku.com");

    String redirectUri = System.getProperty("redirect");
    weibo =
        new Weibo(System.getProperty("weibo.id"), System.getProperty("weibo.secret"), redirectUri);
    connect =
        new QQConnect(System.getProperty("connect.id"), System.getProperty("connect.secret"),
            redirectUri);
    weixin =
        new Weixin(System.getProperty("weixin.id"), System.getProperty("weixin.secret"),
            redirectUri, System.getProperty("weixin.token"));
  }

}
