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
                                    <form id="formLogin" class="form-horizontal" action="${ctx}/login" method="post">
                                        <c:if test="${result!=null&&!result.success}">
                                        <div class="form-group">
                                            <label  class="col-sm-3 control-label">&nbsp;</label>
                                            <div class="col-sm-8">
                                                <div class="alert alert-danger">${result.message}</div>
                                            </div>
                                        </div>
                                        </c:if>

                                        <div class="form-group">
                                            <label for="inputLoginName" class="col-sm-3 control-label">用户名</label>
                                            <div class="col-sm-8">
                                                <input name="loginName" type="text" value="${loginName}" class="form-control input-lg" id="inputLoginName" placeholder="电子邮箱地址">
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
                                                <input name="password" type="password" class="form-control input-lg" id="inputPassword" placeholder="密码">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-8">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" value="1" name="remember"> 7 天内免登录
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
    <jsp:attribute name="js">
        <script type="text/javascript" src="${ctx}/resources/lib/validate/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/lib/validate/localization/messages_zh.js"></script>
        <script type="text/javascript" src="${ctx}/resources/js/user.js"></script>

        <script type="text/javascript">
            (function () {

            })();
        </script>
    </jsp:attribute>
</layout:default>