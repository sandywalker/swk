<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷-会员登录 " classes="gradient">
    <jsp:attribute name="main">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div id="signPanel">
                        <div class="title">
                            <h3>搜文库-会员登录</h3>
                        </div>
                        <div class="body">
                            <div class="row">
                                <div class="col-md-7">
                                    <form class="form-horizontal" method="post">
                                        <div class="form-group">
                                            <label for="inputPhone" class="col-sm-3 control-label">手机号</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control input-lg" id="inputPhone" placeholder="手机号">
                                            </div>
                                        </div>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="inputCode" class="col-sm-3 control-label">验证码</label>--%>
                                            <%--<div class="col-sm-8">--%>
                                                <%--<input type="text" class="form-control input-lg" id="inputCode" placeholder="手机验证码">--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label for="inputPassword" class="col-sm-3 control-label">密码</label>
                                            <div class="col-sm-8">
                                                <input type="password" class="form-control input-lg" id="inputPassword" placeholder="密码">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-8">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox"> 7 天内免登录
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-8">
                                                <button type="submit" class="btn btn-lg btn-warning btn-block">登录</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-5">
                                    <div id="account">
                                        <p>还没有账号？</p>
                                        <a href="/register" class="btn btn-lg btn-info btn-block btn-login">注册新账号</a>
                                        <br><br>
                                        <p>快捷登录</p>
                                        <a href="#" class="btn btn-lg btn-social btn-default btn-block">
                                            <img class="icon" src="${ctx}/resources/img/icon/qq.png" alt="">
                                            使用QQ登录
                                        </a>
                                        <a href="#" class="btn btn-lg btn-social btn-default btn-block">
                                            <img class="icon" src="${ctx}/resources/img/icon/weibo.png" alt="">
                                            使用新浪微博登录
                                        </a>
                                        <a href="#" class="btn btn-lg btn-social btn-default btn-block">
                                            <img class="icon" src="${ctx}/resources/img/icon/wechat.png" alt="">
                                            使用微信登录
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</layout:default>