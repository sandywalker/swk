<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷-会员注册 " classes="gradient">
    <jsp:attribute name="main">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div id="signPanel">
                        <div class="title">
                            <h3>欢迎注册为搜文库会员</h3>
                        </div>
                        <div class="body">
                            <div class="row">
                                <div class="col-md-7">
                                    <form class="form-horizontal" id="regForm" action="${ctx}/register" method="post">
                                        <div class="form-group">
                                            <label for="inputMail" class="col-sm-3 control-label">邮箱</label>
                                            <div class="col-sm-8">
                                                <input type="text" name="mail" class="form-control input-lg" id="inputMail" placeholder="邮箱地址">
                                            </div>
                                        </div>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="inputPhone" class="col-sm-3 control-label">手机号</label>--%>
                                            <%--<div class="col-sm-8">--%>
                                                <%--<input type="text" class="form-control input-lg" id="inputPhone" placeholder="手机号">--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="inputCode" class="col-sm-3 control-label">验证码</label>--%>
                                            <%--<div class="col-sm-8">--%>
                                                <%--<input type="text" class="form-control input-lg" id="inputCode" placeholder="手机验证码">--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label for="inputPassword" class="col-sm-3 control-label">密码</label>
                                            <div class="col-sm-8">
                                                <input type="password" name="password" class="form-control input-lg" id="inputPassword" placeholder="密码">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputConfPassword" class="col-sm-3 control-label">确认密码</label>
                                            <div class="col-sm-8">
                                                <input type="password" name="cpassword" class="form-control input-lg" id="inputConfPassword" placeholder="确认密码">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-8">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" id="readAgree"> 我已阅读  <a href="#" id="showAgree">《用户协议》</a>
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-3 col-sm-8">
                                                <button type="submit" class="btn btn-lg btn-warning btn-block">注册</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-5">
                                    <div id="account">
                                        <p>已有账号？</p>
                                        <a href="/login" class="btn btn-lg btn-success btn-block btn-login">登录已有账号</a>
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
        <jsp:include page="agreement.jsp"/>
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