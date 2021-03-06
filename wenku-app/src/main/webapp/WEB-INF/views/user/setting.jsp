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
                        <li role="presentation" class="active"><a href="${ctx}/user/setting"> <i class="glyphicon glyphicon-user"></i> 账户信息</a></li>
                        <li role="presentation" ><a href="${ctx}/user/cpass"> <i class="glyphicon glyphicon-lock"></i> 修改密码</a></li>
                    </ul>
                    <br>
                    <c:if test="${not empty message}">
                        <div class="alert alert-success">${message}</div>
                    </c:if>
                    <form class="form-horizontal" action="${ctx}/user/setting" method="post" id="myAccount">
                        <div class="form-group">
                            <label  class="col-sm-3 control-label">用户名 (邮箱)：</label>
                            <div class="col-sm-4">
                                <p class="form-text">${user.email}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nickName" class="col-sm-3 control-label">昵称：</label>
                            <div class="col-sm-4">
                                <input type="text"  name="nickName" class="form-control " id="nickName" placeholder="昵称" value="${user.nickName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-3 control-label">手机号：</label>
                            <div class="col-sm-4">
                                <input type="text"  name="phone" class="form-control " id="phone" placeholder="手机号" value="${user.phone}">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-8">
                                <button type="submit" class="btn btn-warning">更新账户信息</button>
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

                $('#myAccount').validate({
                    rules:{
                        nickName:{
                            required:true,
                            maxlength:20
                        },
                        phone:{
                            number:true,
                            maxlength:11,
                            minlength:11
                        }
                    },
                    messages:{
                        nickName:{
                            required:'请填写合适的昵称！'
                        },
                        phone:{
                            number:'请输入正确的手机号',
                            minlength:'请输入正确的手机号',
                            maxlength:'请输入正确的手机号'
                        }
                    }
                });
            })();
        </script>
    </jsp:attribute>
</layout:default>