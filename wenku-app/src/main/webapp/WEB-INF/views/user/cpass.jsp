<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp"%>

<layout:default title="搜文酷 - 账号设置" id="mySpace" classes="my-setting">
    <jsp:attribute name="css">
        <style>
            .form-text{
                line-height: 35px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="main">
        <jsp:include page="../common/searchbar.jsp"/>
        <div class="container my-container">
            <div class="row">
                <div class="col-md-2 sidebar">
                    <div class="profile">
                        <img src="${ctx}/resources/img/icon/user.jpg" alt="" class="avatar">
                        <p class="name">${user.nickName}</p>
                    </div>
                    <div class="upload-button">
                        <a href="${ctx}/user/upload" target="_blank" class="btn btn-info btn-block"> <i class="glyphicon glyphicon-upload"></i> 上传文档</a>
                    </div>

                    <br>
                    <jsp:include page="side-menu.jsp"/>
                </div>
                <div class="col-md-10 main">
                    <br>
                    <ul class="nav nav-tabs">
                        <li role="presentation" ><a href="${ctx}/user/setting"> <i class="glyphicon glyphicon-user"></i> 账户信息</a></li>
                        <li role="presentation" class="active" ><a href="${ctx}/user/cpass"> <i class="glyphicon glyphicon-lock"></i> 修改密码</a></li>
                    </ul>
                    <br>
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">${message}</div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div class="alert alert-error">${error}</div>
                    </c:if>
                    <form class="form-horizontal" action="${ctx}/user/cpass" method="post" id="changePass">

                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">当前密码：</label>
                            <div class="col-sm-4">
                                <input type="password"  name="password" class="form-control " id="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newPass" class="col-sm-3 control-label">新密码：</label>
                            <div class="col-sm-4">
                                <input type="password"  name="newPass" class="form-control " id="newPass">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="newPassConfirm" class="col-sm-3 control-label">确认新密码：</label>
                            <div class="col-sm-4">
                                <input type="password"  name="newPassConfirm" class="form-control " id="newPassConfirm">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-8">
                                <button type="submit" class="btn btn-warning">更新密码</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <p>&nbsp;</p>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script type="text/javascript" src="${ctx}/resources/lib/validate/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/lib/validate/localization/messages_zh.js"></script>
        <script>
            (function () {

                $('#changePass').validate({
                    rules:{
                        mail:{
                            required:true,
                            email:true,
                            maxlength:32,
                            remote: ctx + "/register/check/email?mail=" + $('#inputMail').val()
                        },
                        password:{
                            required:true,
                            remote: {
                                type:'POST',
                                url:ctx + '/user/vpass',
                                data:{pass:function(){
                                    return $('#password').val();
                                }}
                            }
                        },
                        newPass:{
                            required:true,
                            minlength:8,
                            maxlength:16,
                        },
                        newPassConfirm:{
                            required:true,
                            minlength:8,
                            maxlength:16,
                            equalTo:'#newPass'
                        }
                    },
                    messages:{
                        password:{
                            remote:'当前密码不正确！'
                        },
                        newPass:{
                            minlength:'密码长度至少需要 8 位！',
                            maxlength:'密码长度不能 16 位！',
                        },
                        newPassConfirm:{
                            equalTo:'密码两次输入的不一致，请确保密码输入一致！'
                        }
                    }
                });
            })();
        </script>
    </jsp:attribute>
</layout:default>